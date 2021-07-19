package com.example.tlunet.ui.subject

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.tlunet.R
import com.example.tlunet.extensions.titleNav
import com.example.tlunet.model.comments.Comment
import com.example.tlunet.model.documents.Document
import com.example.tlunet.model.subjects.Subjects
import com.example.tlunet.navigation.Navigation
import com.example.tlunet.utils.Preferences
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_subject_detail.*


class SubjectDetailActivity : BaseActivity<SubjectDetailPresenter>(), SubjectDetailContract.View {
    private lateinit var adapterReDoc : ReDocAdapter
    private lateinit  var adapterDoc : DocAdapter
    private lateinit var adapterComment : CommentAdapter

    override fun getLayoutId(): Int {
        return R.layout.activity_subject_detail
    }

    override fun init() {
        val title = intent.getStringExtra(titleNav)
        navBar.setTitle(title!!.toString())
        navBar.setBackPressListener {
            finish()
        }
        val  userData = Preferences.getInstance().getUserData() ?: return
        if(userData.role == 1) {
            btnShare.visibility = View.VISIBLE
            btnShare.isClickable = true
            btnShare.isEnabled = true
            btnShare.setOnClickListener {
                Navigation.toDocumentActivity(this)
            }
            btnComment.visibility = View.GONE
            btnComment.isClickable = false
            btnComment.isEnabled = false
        }
        else {
            btnShare.visibility = View.GONE
            btnShare.isClickable = false
            btnShare.isEnabled = false
            btnComment.visibility = View.VISIBLE
            btnComment.isClickable = true
            btnComment.isEnabled = true
            btnComment.setOnClickListener {
                Navigation.toCommentActivity(this,mPresenter.subjectCode)
            }
        }
        mPresenter.fetchSubject(intent)
        mPresenter.fetchDocuments(intent)
        mPresenter.fetchComments(intent)

        tvLesson.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(tvLesson.text.toString()))
            startActivity(browserIntent)
        }
        adapterReDoc = ReDocAdapter(this)
//        adapterReDoc.setOnItemClickListener {
//
//        }
        rvReferenceDocuments.adapter = adapterReDoc
        rvReferenceDocuments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)

        adapterDoc = DocAdapter(this)

        rvDocuments.adapter = adapterDoc
        rvDocuments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        adapterDoc.setOnItemClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it.documentUrl.toString()))
            startActivity(browserIntent)
        }
        adapterComment = CommentAdapter(this)
//        adapterReDoc.setOnItemClickListener {
//
//        }
        rvComments.adapter = adapterComment
        rvComments.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)



    }

    override fun initPresenter(): SubjectDetailPresenter {
        return SubjectDetailPresenter()
    }

    override fun fillSubject(subject: Subjects?) {
        with(subject) {
            tvName.text =" " + subject?.name
            tvCode.text = " " + subject?.code
            tvCredit.text = " " + subject?.credit.toString() + " " +  getString(R.string.credit)
            Glide.with(applicationContext!!).load(subject?.imgUrl).listener(object :
                RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress.visibility = View.VISIBLE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progress.visibility = View.GONE
                    return false
                }
            }).into(img)
            val spannableString = SpannableString(subject?.lesson ).apply {
                setSpan(UnderlineSpan(), 0, subject?.lesson!!.length, 0)
            }
            tvLesson.text = spannableString
            adapterReDoc.appendData(subject?.referenceDocuments!!)
        }
    }

    override fun fillDocuments(listDocuments: List<Document>?) {
        if(listDocuments!=null){
            adapterDoc.appendData(listDocuments)
        }
    }

    override fun fillComments(listComments: List<Comment>?) {
        if(listComments!=null){
            adapterComment.appendData(listComments)
        }
    }

    override fun onResume() {
        super.onResume()
        mPresenter.fetchDocuments(intent)
        mPresenter.fetchComments(intent)
    }
}