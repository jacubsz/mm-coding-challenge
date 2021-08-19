package com.github.jacubsz.mm.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.jacubsz.mm.R
import com.github.jacubsz.mm.databinding.ActivityAboutBinding
import com.github.jacubsz.mm.viewmodel.EmptyViewModel

class AboutActivity : AppActivity<ActivityAboutBinding, EmptyViewModel>(
    R.layout.activity_about,
    EmptyViewModel::class
) {

    companion object {

        fun newIntent(context: Context) = Intent(context, AboutActivity::class.java)

    }

    override fun initView(savedInstanceState: Bundle?) {}

}