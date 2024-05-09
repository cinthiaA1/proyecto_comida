package com.example.proyecto_comida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_comida.databinding.ActivitySingUpBinding
import com.example.proyecto_comida.databinding.ActivitySingingBinding
import com.google.firebase.auth.FirebaseAuth

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingUpBinding
    private lateinit var firebaseauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseauth = FirebaseAuth.getInstance()
        //Si ya tiene cuenta ir al sing in
        binding.tvGoToSingIn.setOnClickListener{
            val intent = Intent(this, SingingActivity::class.java)
            startActivity(intent)
        }

        binding.btnSingUp.setOnClickListener {
            val email = binding.teEmail.text.toString()
            val pass = binding.tePass.text.toString()
            val pass2 = binding.tePass2.text.toString()

            if (email.isEmpty() or pass.isEmpty() or pass2.isEmpty()) {
                Toast.makeText(this, "Verifica la entrada", Toast.LENGTH_SHORT).show()
            } else {
                if (pass == pass2) {
                    firebaseauth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            //Redirigir a login (pagina de login)
                            val intent = Intent(this, SingingActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(
                        this,
                        "NO coinciden las contraseñas",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
}