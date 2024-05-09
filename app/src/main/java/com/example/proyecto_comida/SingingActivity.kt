package com.example.proyecto_comida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.proyecto_comida.databinding.ActivitySingingBinding
import com.google.firebase.auth.FirebaseAuth

class SingingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingingBinding
    private lateinit var firebaseauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingingBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseauth = FirebaseAuth.getInstance()
        //Si no se tiene cuenta ir a sing up
        binding.tvGoToSingUp.setOnClickListener{
            val intent = Intent(this, SingUpActivity::class.java)
            startActivity(intent)
        }

        binding.btnSingIn.setOnClickListener {
            val email = binding.teEmail.text.toString()
            val pass = binding.tePass.text.toString()

            if (email.isEmpty() or pass.isEmpty()){
                Toast.makeText(this, "Verifica la entrada", Toast.LENGTH_SHORT).show()
            }else{
                firebaseauth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if(it.isSuccessful){
                        //Redirigir a mainActivity(página principal)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        firebaseauth.signOut()
    }
}