package com.example.tlunet.ui.postDocument

import com.example.tlunet.R
import com.example.tlunet.extensions.alert
import com.example.tlunet.model.subjects.SubjectOption
import com.example.tlunet.view.DoSuccessfulDialog
import com.mespitech.mvpbase.coremvp.BaseActivity
import kotlinx.android.synthetic.main.activity_document.*

class DocumentActivity : BaseActivity<DocumentActivityPresenter>(),DocumentActivityContract.View {
    private  var selectStatusTypeDialog: SelectDialog<SubjectOption>? = null
    private  var selectedOption: SubjectOption ? = null


    override fun getLayoutId(): Int {
        return  R.layout.activity_document
    }
    override fun init() {
        mPresenter.fetchSubjectOption()
        navBar.setOnClickListener { finish() }

        itemStatus.setOnClickListener {
            showSelectProvince()

        }
        btn_upload.setOnClickListener {
            if(edtUrl.text.toString().trim().isNotEmpty()&&selectedOption!= null){
                mPresenter.postDocument(selectedOption!!.code.toString(), edtUrl.text.toString(),intent)
            } else{
                alert("Giữ liệu không được để trống")
            }
        }
    }

    override fun initPresenter(): DocumentActivityPresenter {
        return DocumentActivityPresenter()
    }

    override fun onError(message: String) {
        alert(message)
    }

    override fun onSuccess(message: String) {
        DoSuccessfulDialog(this,message) {
            finish()
        }.show()
    }

    override fun fillSubjectOption(list: List<SubjectOption>) {
        if(selectStatusTypeDialog == null){
            initSelectStatusTypeDialog(list)
        }
    }

    private fun initSelectStatusTypeDialog(listStatusType : List<SubjectOption>) {
        val listData = ArrayList<SubjectOption>()
        listData.addAll(listStatusType)
        selectStatusTypeDialog = SelectDialog(
                this,
                listData,
                { statusType -> statusType.name.toString()},
                0
        )
        itemStatus?.setTitle("Chọn môn học")
        selectStatusTypeDialog?.setTitle("Chọn môn học")
        selectStatusTypeDialog?.setOnSelectedListener {it->
            selectedOption = it
            itemStatus.setTitle(it.name.toString())
            selectStatusTypeDialog?.dismiss()
        }
    }

    private fun showSelectProvince(){
        selectStatusTypeDialog?.show()
    }
}