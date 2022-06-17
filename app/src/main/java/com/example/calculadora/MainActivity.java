package com.example.calculadora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button numeroZero,numeroUm, numeroDois, numeroTres, numeroQuatro, numeroCinco, numeroSeis, numeroSete,
    numeroOito, numeroNove, ponto, soma, subtracao, multiplicacao, divisao, igual, botao_limpar;

    private TextView txtExpressao, txtResultado;

    private ImageView backspace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IniciarComponentes();
        getSupportActionBar().hide(); //esconder a barra de ações

        numeroZero.setOnClickListener(this); //defino os eventos pra recuperar o clique
        numeroUm.setOnClickListener(this);
        numeroDois.setOnClickListener(this);
        numeroTres.setOnClickListener(this);
        numeroQuatro.setOnClickListener(this);
        numeroCinco.setOnClickListener(this);
        numeroSeis.setOnClickListener(this);
        numeroSete.setOnClickListener(this);
        numeroOito.setOnClickListener(this);
        numeroNove.setOnClickListener(this);
        ponto.setOnClickListener(this);
        soma.setOnClickListener(this);
        subtracao.setOnClickListener(this);
        multiplicacao.setOnClickListener(this);
        divisao.setOnClickListener(this);

        botao_limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //quando clico no botao de limpar
                txtExpressao.setText(""); //limpo a expressao
                txtResultado.setText(""); //e limpo tb o resultado
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView expressao = findViewById(R.id.txt_expressao); //recupero o id da expressao
                String string = expressao.getText().toString(); //pego oq foi digitado e transformo pra string

                if(!string.isEmpty()){ //se a string n estiver vazia
                    byte var0 = 0;
                    int var1 = string.length()-1; //tam da string -1
                    String txtExpressao = string.substring(var0, var1);
                    expressao.setText(txtExpressao); //expressao -1 posicao
                }
                txtResultado.setText("");
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Expression expressao = new ExpressionBuilder(txtExpressao.getText().toString()).build(); //ExpressionBuilder que vai ser responsavel por fazer todos os calculos
                    double resultado = expressao.evaluate(); //resultado recebe a expressao com o metodo evaluate que avalia a expressao e trata se necessario
                    long longResult = (long) resultado;

                    if(resultado == (double)longResult){
                        txtResultado.setText((CharSequence) String.valueOf(longResult));
                        //txt resultado recebe o longResult e dps converto ele pra String
                    }else{
                        txtResultado.setText((CharSequence) String.valueOf(resultado));
                    }
                }catch (Exception e) {

                }
            }
        });

    }

    private void IniciarComponentes(){ //recuperando os ids
        numeroZero = findViewById(R.id.numero_zero);
        numeroUm = findViewById(R.id.numero_um);
        numeroDois = findViewById(R.id.numero_dois);
        numeroTres = findViewById(R.id.numero_tres);
        numeroQuatro = findViewById(R.id.numero_quatro);
        numeroCinco = findViewById(R.id.numero_cinco);
        numeroSeis = findViewById(R.id.numero_seis);
        numeroSete = findViewById(R.id.numero_sete);
        numeroOito = findViewById(R.id.numero_oito);
        numeroNove = findViewById(R.id.numero_nove);
        ponto = findViewById(R.id.ponto);
        soma = findViewById(R.id.soma);
        subtracao = findViewById(R.id.subtracao);
        multiplicacao = findViewById(R.id.multiplicacao);
        divisao = findViewById(R.id.divisao);
        igual = findViewById(R.id.igual);
        botao_limpar = findViewById(R.id.bt_limpar);
        txtExpressao = findViewById(R.id.txt_expressao);
        txtResultado = findViewById(R.id.txt_resultado);
        backspace = findViewById(R.id.backspace);
    }

    public void AcrescentarUmaExpressao(String string, boolean limpar_dados){
        if(txtResultado.getText().equals("")){ //se meu resultado estiver vazio
            txtExpressao.setText(" "); //minha expressao fica vazia
        }

        if(limpar_dados){ //se for true
            txtResultado.setText(" "); //limpo o campo de resultado
            txtExpressao.append(string); //passo minha expressao (conta)
        }else{
            txtExpressao.append(txtResultado.getText()); //acrescento oq foi digitado e o resultado
            txtExpressao.append(string);
            txtResultado.setText(" "); //limpo o resultado
        }
    }

    @Override
    public void onClick(View view) { //metodo pra capturar o click dos botoes
        switch (view.getId()){ //recupero o id do botao clicado
            case R.id.numero_zero: //se o id for "numero_zero"
                AcrescentarUmaExpressao("0", true); //coloco na expressao o "0"
                break;

            case R.id.numero_um:
                AcrescentarUmaExpressao("1", true);
                break;

            case R.id.numero_dois:
                AcrescentarUmaExpressao("2", true);
                break;

            case R.id.numero_tres:
                AcrescentarUmaExpressao("3", true);
                break;

            case R.id.numero_quatro:
                AcrescentarUmaExpressao("4", true);
                break;

            case R.id.numero_cinco:
                AcrescentarUmaExpressao("5", true);
                break;

            case R.id.numero_seis:
                AcrescentarUmaExpressao("6", true);
                break;

            case R.id.numero_sete:
                AcrescentarUmaExpressao("7", true);
                break;

            case R.id.numero_oito:
                AcrescentarUmaExpressao("8", true);
                break;

            case R.id.numero_nove:
                AcrescentarUmaExpressao("9", true);
                break;

            case R.id.ponto:
                AcrescentarUmaExpressao(".", true);
                break;

            case R.id.soma:
                AcrescentarUmaExpressao("+", false);
                break;

            case R.id.subtracao:
                AcrescentarUmaExpressao("-", false);
                break;

            case R.id.multiplicacao:
                AcrescentarUmaExpressao("*", false);
                break;

            case R.id.divisao:
                AcrescentarUmaExpressao("/", false);
                break;
        }
    }
}