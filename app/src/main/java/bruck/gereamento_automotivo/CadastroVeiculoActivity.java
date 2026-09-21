package bruck.gereamento_automotivo;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CadastroVeiculoActivity extends AppCompatActivity {

    private EditText etApelido;
    private EditText etModelo;
    private EditText etKmAtual;
    private Spinner spMarca;
    private RadioGroup rgCombustivel;
    private CheckBox cbEmUso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculo);

        etApelido = findViewById(R.id.etApelido);
        etModelo = findViewById(R.id.etModelo);
        etKmAtual = findViewById(R.id.etKmAtual);
        spMarca = findViewById(R.id.spMarca);
        rgCombustivel = findViewById(R.id.rgCombustivel);
        cbEmUso = findViewById(R.id.cbEmUso);
    }

    public void limparFormulario(View view) {
        etApelido.setText("");
        etModelo.setText("");
        etKmAtual.setText("");
        rgCombustivel.clearCheck();
        cbEmUso.setChecked(false);
        spMarca.setSelection(0);

        Toast.makeText(this, R.string.toast_formulario_limpo, Toast.LENGTH_LONG).show();
    }

    public void salvarFormulario(View view) {
        String apelido = etApelido.getText().toString().trim();
        String modelo = etModelo.getText().toString().trim();
        String kmAtual = etKmAtual.getText().toString().trim();

        if (apelido.isEmpty()) {
            Toast.makeText(this, R.string.erro_apelido_vazio, Toast.LENGTH_LONG).show();
            etApelido.requestFocus();
            return;
        }
        if (modelo.isEmpty()) {
            Toast.makeText(this, R.string.erro_modelo_vazio, Toast.LENGTH_LONG).show();
            etModelo.requestFocus();
            return;
        }
        if (kmAtual.isEmpty()) {
            Toast.makeText(this, R.string.erro_km_vazio, Toast.LENGTH_LONG).show();
            etKmAtual.requestFocus();
            return;
        }

        int idCombustivelSelecionado = rgCombustivel.getCheckedRadioButtonId();

        String combustivel;

        if (idCombustivelSelecionado == R.id.rbGasolina) {
            combustivel = getString(R.string.radio_gasolina);
        } else if (idCombustivelSelecionado == R.id.rbEtanol) {
            combustivel = getString(R.string.radio_etanol);
        } else if (idCombustivelSelecionado == R.id.rbDiesel) {
            combustivel = getString(R.string.radio_diesel);
        } else {
            Toast.makeText(this, R.string.erro_combustivel_nao_selecionado, Toast.LENGTH_LONG).show();
            return;
        }

        String marca = spMarca.getSelectedItem().toString();
        boolean emUso = cbEmUso.isChecked();

        String resumo = getString(R.string.titulo_cadastro_veiculo) + ": "
                + apelido + " / " + modelo + " / " + marca
                + " / " + kmAtual + " km / " + combustivel
                + " / em uso: " + emUso;

        Toast.makeText(this, resumo, Toast.LENGTH_LONG).show();
    }
}
