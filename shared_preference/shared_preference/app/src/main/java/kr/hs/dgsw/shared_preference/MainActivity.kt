package kr.hs.dgsw.shared_preference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSave = findViewById<Button>(R.id.btn_save)
        var editText = findViewById<EditText>(R.id.editText)

        val pref = this.getPreferences(0)
        val editor = pref.edit()
        /* context.getPreferences의 SharedPreferences 인스턴스를 저장.
         * (0은 (Context.MODE_PRIVATE)와 같음)
         * 에디터를 호출해 editor로 초기화. */

        editText.setText(pref.getString("MessageKey", ""))
        /* mainEt(EditText)의 텍스트를 "MessageKey"에 해당하는 vaule로 설정.
         * 값을 불러오지 못했을 경우, default vaule는 ""로 지정. */

        btnSave.setOnClickListener {
            editor.putString("MessageKey", editText.text.toString()).apply()
            // btnSave(Button)을 클릭하면 "MessageKey"에 해당하는 String 데이터를 EditText에서 불러와 저장.

            val msg = pref.getString("MessageKey", "")
            if (msg == "") {
                Toast.makeText(this, "텍스트가 초기화되었습니다.", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "저장됨: $msg", Toast.LENGTH_LONG).show()
            }
        }
    }
}