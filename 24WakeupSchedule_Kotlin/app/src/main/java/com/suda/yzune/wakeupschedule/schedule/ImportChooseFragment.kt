package com.suda.yzune.wakeupschedule.schedule

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.BaseDialogFragment
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.suda.yzune.wakeupschedule.R
import com.suda.yzune.wakeupschedule.schedule_import.LoginWebActivity
import com.suda.yzune.wakeupschedule.schedule_import.SchoolListActivity
import com.suda.yzune.wakeupschedule.utils.Const
import com.suda.yzune.wakeupschedule.utils.Utils
import kotlinx.android.synthetic.main.fragment_import_choose.*
import splitties.activities.start

class ImportChooseFragment : BaseDialogFragment() {

    override val layoutId: Int
        get() = R.layout.fragment_import_choose

    private val viewModel by activityViewModels<ScheduleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initEvent()
    }

    private fun initEvent() {
        ib_close.setOnClickListener {
            dismiss()
        }

        tv_file.setOnClickListener {
            showSAFTips {
                activity!!.startActivityForResult(
                        Intent(activity, LoginWebActivity::class.java).apply {
                            putExtra("import_type", "file")
                        },
                        Const.REQUEST_CODE_IMPORT)
                this.dismiss()
            }
        }

        tv_html.setOnClickListener {
            showSAFTips {
                activity!!.startActivityForResult(
                        Intent(activity, LoginWebActivity::class.java).apply {
                            putExtra("import_type", "html")
                            putExtra("tableId", viewModel.table.id)
                        },
                        Const.REQUEST_CODE_IMPORT)
                this.dismiss()
            }
        }

        tv_excel.setOnClickListener {
            showSAFTips {
                activity!!.startActivityForResult(
                        Intent(activity, LoginWebActivity::class.java).apply {
                            putExtra("import_type", "excel")
                            putExtra("tableId", viewModel.table.id)
                        },
                        Const.REQUEST_CODE_IMPORT)
                this.dismiss()
            }
        }

        tv_school.setOnClickListener {
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            activity!!.startActivityForResult(
                    Intent(activity, SchoolListActivity::class.java),
                    Const.REQUEST_CODE_IMPORT)
            dismiss()
        }

        tv_feedback.setOnClickListener {
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            activity!!.start<LoginWebActivity> {
                putExtra("import_type", "apply")
            }
            dismiss()
        }
    }

    private fun showSAFTips(block: () -> Unit) {
        MaterialAlertDialogBuilder(activity)
                .setTitle("??????")
                .setMessage("????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????")
                .setNeutralButton("??????????????????") { _, _ ->
                    Utils.openUrl(activity!!, "https://support.qq.com/embed/phone/97617/faqs/59884")
                }
                .setPositiveButton(R.string.sure) { _, _ ->
                    block.invoke()
                }
                .setNegativeButton(R.string.cancel, null)
                .setCancelable(false)
                .show()
    }

}
