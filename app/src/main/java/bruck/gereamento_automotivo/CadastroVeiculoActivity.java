package bruck.gereamento_automotivo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class CadastroVeiculoActivity extends AppCompatActivity {

    public static final String KEY_APELIDO     = "KEY_APELIDO";
    public static final String KEY_MODELO      = "KEY_MODELO";
    public static final String KEY_KM_ATUAL    = "KEY_KM_ATUAL";
    public static final String KEY_MARCA       = "KEY_MARCA";
    public static final String KEY_COMBUSTIVEL = "KEY_COMBUSTIVEL";
    public static final String KEY_EM_USO      = "KEY_EM_USO";
    public static final String KEY_MODO        = "MODO";

    public static final int MODO_NOVO   = 0;
    public static final int MODO_EDITAR = 1;

    private EditText etApelido;
    private EditText etModelo;
    private EditText etKmAtual;
    private Spinner spMarca;
    private RadioGroup rgCombustivel;
    private CheckBox cbEmUso;

    private int modo;
    private Veiculo veiculoOriginal;

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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            modo = bundle.getInt(KEY_MODO);
        }

        if (modo == MODO_EDITAR) {
            setTitle(R.string.editar_veiculo);

            String apelido     = bundle.getString(KEY_APELIDO);
            String modelo      = bundle.getString(KEY_MODELO);
            int kmAtual        = bundle.getInt(KEY_KM_ATUAL);
            String marca       = bundle.getString(KEY_MARCA);
            String combustivel = bundle.getString(KEY_COMBUSTIVEL);
            boolean emUso      = bundle.getBoolean(KEY_EM_USO);

            veiculoOriginal = new Veiculo(apelido, modelo, kmAtual, marca, combustivel, emUso);

            etApelido.setText(apelido);
            etModelo.setText(modelo);
            etKmAtual.setText(String.valueOf(kmAtual));
            cbEmUso.setChecked(emUso);

            String[] marcas = getResources().getStringArray(R.array.marcas_veiculo);

            for (int posicao = 0; posicao < marcas.length; posicao++) {
                if (marcas[posicao].equals(marca)) {
                    spMarca.setSelection(posicao);
                }
            }

            if (combustivel.equals(getString(R.string.radio_gasolina))) {
                rgCombustivel.check(R.id.rbGasolina);
            } else if (combustivel.equals(getString(R.string.radio_etanol))) {
                rgCombustivel.check(R.id.rbEtanol);
            } else if (combustivel.equals(getString(R.string.radio_diesel))) {
                rgCombustivel.check(R.id.rbDiesel);
            }
        } else {
            setTitle(R.string.novo_veiculo);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_veiculo_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuItemSalvar) {
            salvarFormulario();
            return true;
        } else if (id == R.id.menuItemLimpar) {
            limparFormulario();
            return true;
        } else if (id == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void limparFormulario() {
        etApelido.setText("");
        etModelo.setText("");
        etKmAtual.setText("");
        rgCombustivel.clearCheck();
        cbEmUso.setChecked(false);
        spMarca.setSelection(0);

        Toast.makeText(this, R.string.toast_formulario_limpo, Toast.LENGTH_LONG).show();
    }

    private void salvarFormulario() {
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

        int kmAtualNumero;

        try {
            kmAtualNumero = Integer.parseInt(kmAtual);

        } catch (NumberFormatException e) {

            Toast.makeText(this, R.string.erro_km_invalido, Toast.LENGTH_LONG).show();
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

        if (modo == MODO_EDITAR
                && apelido.equals(veiculoOriginal.getApelido())
                && modelo.equals(veiculoOriginal.getModelo())
                && kmAtualNumero == veiculoOriginal.getKmAtual()
                && marca.equals(veiculoOriginal.getMarca())
                && combustivel.equals(veiculoOriginal.getCombustivel())
                && emUso == veiculoOriginal.isEmUso()) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }

        Intent intentResposta = new Intent();

        intentResposta.putExtra(KEY_APELIDO, apelido);
        intentResposta.putExtra(KEY_MODELO, modelo);
        intentResposta.putExtra(KEY_KM_ATUAL, kmAtualNumero);
        intentResposta.putExtra(KEY_MARCA, marca);
        intentResposta.putExtra(KEY_COMBUSTIVEL, combustivel);
        intentResposta.putExtra(KEY_EM_USO, emUso);

        setResult(CadastroVeiculoActivity.RESULT_OK, intentResposta);

        finish();
    }
}
