package matc89.exercicio2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OutraActivity extends AppCompatActivity implements View.OnClickListener {
    public EditText editText;
    private String nomeAlterado;
    private Button btnConfirmar;
    private Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);

        Intent intent = getIntent();
        nomeAlterado = intent.getStringExtra("nomeAlterado");

        editText = (EditText) findViewById(R.id.editText);

        editText.setText(nomeAlterado, TextView.BufferType.EDITABLE);
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    editText.setText("");
                }
                return false;
            }
        });

        btnConfirmar = (Button) findViewById(R.id.btnConfirmar);
        btnConfirmar.setOnClickListener(this);

        btnCancelar = (Button) findViewById(R.id.btnCancelar);
        btnCancelar.setOnClickListener(this);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String meutexto = savedInstanceState.getString("meutexto");
        editText.setText(meutexto);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("meutexto", editText.getText().toString());
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent data;
        switch (id) {
            case R.id.btnConfirmar:
                String nome = editText.getText().toString();
                data = new Intent();
                data.putExtra("nomeAlterado", nome);
                setResult(RESULT_OK, data);
                finish();
                break;
            case R.id.btnCancelar:
                data = new Intent();
                setResult(RESULT_CANCELED, data);
                finish();
                break;
        }
    }
}
