package brecori.github.com.listadecompras

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.RecyclerView
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import brecori.github.com.listadecompras.ui.theme.ListaDeComprasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        val editValue = findViewById<EditText>(R.id.editValue)
        val totalValue = findViewById<TextView>(R.id.totalView)


        fun hideKeyboard(context: Context, view: View) {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }

        button.setOnClickListener {

            if (editText.text.isBlank()) {
                editText.error = "Campo obrigatório"
            }
            else if(editValue.text.isBlank()) {
                editValue.error = "Campo obrigatório"
            }
            else {
                val item = ItemModel(
                    name = editText.text.toString(),
                    value = editValue.text.toString().toDouble()
                )

                itemsAdapter.addItem(item)
                editText.setText("");
                editValue.setText("");
            }

            totalValue.text = "Total: R$ " + itemsAdapter.totalValue()
            hideKeyboard(this, it)
        }
}
}
