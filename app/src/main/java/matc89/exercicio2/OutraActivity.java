package matc89.exercicio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OutraActivity extends AppCompatActivity {
    public EditText editText;
    private TextView labelMensagem;
    private Button btnConfirmar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);

        Intent intent = getIntent();
        String usuarioAtual = intent.getStringExtra("usuarioAtual");
//        startActivity(intent);

        labelMensagem = (TextView)findViewById(R.id.editText);
        final EditText editText = (EditText)findViewById(R.id.editText);
        editText.setText(usuarioAtual, TextView.BufferType.EDITABLE);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    editText.setText("");
                }
                return false;
            }
        });

        btnConfirmar = (Button)findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = editText.getText().toString();
                Intent data = new Intent();
                data.putExtra("nomeAlterado", nome);
                setResult(RESULT_OK, data);
                finish();
            }
        });

        btnCancelar = (Button)findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent();
                setResult(RESULT_CANCELED, data);
                finish();
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String meutexto = savedInstanceState.getString("meutexto");
        labelMensagem.setText(meutexto);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("meutexto", labelMensagem.getText().toString());
    }

}
