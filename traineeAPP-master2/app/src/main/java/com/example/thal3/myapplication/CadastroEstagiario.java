package com.example.thal3.myapplication;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thal3.myapplication.dominio.Curriculo;
import com.example.thal3.myapplication.dominio.Pessoa;
import com.example.thal3.myapplication.negocio.EstagiarioServices;
import com.example.thal3.myapplication.persistencia.ControladorBanco;
import com.example.thal3.myapplication.persistencia.Database;

import java.util.ArrayList;

public class CadastroEstagiario extends AppCompatActivity {

    Button cadastrar;

    Database db = new Database(this);
    ControladorBanco db2 = new ControladorBanco(this);

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtCPF;
    private EditText edtSenha;
    private EditText edtConfSenha;
    private EditText edtCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estagiario);

        edtNome = (EditText)findViewById(R.id.editNomeCadastroEst);
        edtEmail = (EditText)findViewById(R.id.editEmailCadastroEst);
        edtCPF = (EditText)findViewById(R.id.editCpf);
        edtSenha = (EditText)findViewById(R.id.editSenhaCadastro);
        edtConfSenha = (EditText)findViewById(R.id.editConfirmaSenha);
        edtCidade = (EditText)findViewById(R.id.editCidade);

        cadastrar = (Button)findViewById(R.id.cadastrar);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultado;

                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String cpf = edtCPF.getText().toString();
                String senha = edtSenha.getText().toString();
                String confSenha = edtConfSenha.getText().toString();
                String cidade = edtCidade.getText().toString();

                resultado = db2.insereDado(nome, cpf, senha,cidade);
                Toast.makeText(CadastroEstagiario.this, resultado, Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean validaCampos() {
        boolean camposVazios = false;
        ArrayList<String> logError = new ArrayList<>();

        String nome = edtNome.getText().toString();
        String email = edtEmail.getText().toString();
        String cpf = edtCPF.getText().toString();
        String senha = edtSenha.getText().toString();
        String confSenha = edtConfSenha.getText().toString();
        String cidade = edtCidade.getText().toString();

        if (camposVazios = isCampoVazio(nome)) {
            edtNome.requestFocus();
        } else if (camposVazios = isCampoVazio(email)) {
            edtEmail.requestFocus();
        } else if (camposVazios = isCampoVazio(cpf)) {
            edtCPF.requestFocus();
        } else if (camposVazios = isCampoVazio(senha)) {
            edtSenha.requestFocus();
        } else if (camposVazios = isCampoVazio(confSenha)) {
            edtConfSenha.requestFocus();
        }
          else if (camposVazios = isCampoVazio(cidade)) {
            edtCidade.requestFocus();
        }
        if (camposVazios) {
            logError.add("- Preencha todos os campos vazios.");
        }

        if (!EstagiarioServices.isEmailValido(email)) {
            logError.add("- O email não é válido.");
        }

        if (!EstagiarioServices.isCPF(cpf)) {
            logError.add("- O CPF não é válido.");
        }

        if (!EstagiarioServices.isSenhaIgual(senha, confSenha)) {
            logError.add("- As senhas não conferem.");
        }

        if (logError.size() > 0) {
            String msg = new String();
            for (String erro : logError) {
                msg = msg.concat(erro + "\n");
            }
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Aviso");
            dlg.setMessage(msg);
            dlg.setNeutralButton("OK", null);
            dlg.show();
            return false;
        } return true;
    }

    private boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }
}
