package com.example.fundamentalexercise2

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var emailButton: Button? = null
    private var editText: EditText? = null
    private var linkToTelegram: ImageView? = null
    private var linkToInsta: ImageView? = null
    private var linkToWhatsapp: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.message_field)
        emailButton = findViewById(R.id.email_button)
        linkToTelegram = findViewById(R.id.social1)
        linkToInsta = findViewById(R.id.social2)
        linkToWhatsapp = findViewById(R.id.social3)

        emailButton?.setOnClickListener {
            onEmailButtonClicked()
        }

        linkToTelegram?.setOnClickListener {
            onSocialLinkClicked(TELEGRAM_ADDRESS)
        }

        linkToInsta?.setOnClickListener {
            onSocialLinkClicked(INSTA_ADDRESS)
        }

        linkToWhatsapp?.setOnClickListener {
            onSocialLinkClicked(WHATSAPP_ADDRESS)
        }
    }

    private fun onEmailButtonClicked() {
        val emailRecipient = arrayOf(getString(R.string.simons_email_adress))
        val emailSubject = getString(R.string.subject_of_email)
        val emailMessage = editText?.text.toString()

        val sendIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(MAIL_TO_URI)
            putExtra(Intent.EXTRA_EMAIL, emailRecipient)
            putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            putExtra(Intent.EXTRA_TEXT, emailMessage)
        }
        try {
            startActivity(sendIntent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.no_email_app_found), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onSocialLinkClicked(url: String) {
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, getString(R.string.no_browser_found), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val MAIL_TO_URI = "mailto:"
        const val TELEGRAM_ADDRESS = "https://t.me/Simon_1"
        const val INSTA_ADDRESS = "https://www.instagram.com/egorovsimon1"
        const val WHATSAPP_ADDRESS = "https://wa.me/+358408509262"
    }
}