package com.example.fundamentalexercise2.aboutme

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fundamentalexercise2.R

class AboutMeFragment : Fragment(R.layout.fragment_about_me) {

    private var emailButton: Button? = null
    private var editText: EditText? = null
    private var linkToTelegram: ImageView? = null
    private var linkToInsta: ImageView? = null
    private var linkToWhatsapp: ImageView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()

    }

    private fun initViews() {
        editText = view?.findViewById(R.id.messageField)
        emailButton = view?.findViewById(R.id.emailButton)
        linkToTelegram = view?.findViewById(R.id.social1)
        linkToInsta = view?.findViewById(R.id.social2)
        linkToWhatsapp = view?.findViewById(R.id.social3)
    }

    private fun initListeners() {
        emailButton?.setOnClickListener { onEmailButtonClicked() }
        linkToTelegram?.setOnClickListener { onSocialLinkClicked(TELEGRAM_ADDRESS) }
        linkToInsta?.setOnClickListener { onSocialLinkClicked(INSTA_ADDRESS) }
        linkToWhatsapp?.setOnClickListener { onSocialLinkClicked(WHATSAPP_ADDRESS) }
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
            Toast.makeText(context, getString(R.string.no_email_app_found), Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun onSocialLinkClicked(url: String) {
        val webPage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, getString(R.string.no_browser_found), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val MAIL_TO_URI = "mailto:"
        const val TELEGRAM_ADDRESS = "https://t.me/Simon_1"
        const val INSTA_ADDRESS = "https://www.instagram.com/egorovsimon1"
        const val WHATSAPP_ADDRESS = "https://wa.me/+358408509262"
    }
}