package com.suda.yzune.wakeupschedule.schedule_settings

import android.app.DatePickerDialog
import android.appwidget.AppWidgetManager
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.suda.yzune.wakeupschedule.BuildConfig
import com.suda.yzune.wakeupschedule.DonateActivity
import com.suda.yzune.wakeupschedule.R
import com.suda.yzune.wakeupschedule.base_view.BaseListActivity
import com.suda.yzune.wakeupschedule.bean.TableBean
import com.suda.yzune.wakeupschedule.bean.TableSelectBean
import com.suda.yzune.wakeupschedule.schedule.DonateFragment
import com.suda.yzune.wakeupschedule.schedule_manage.ScheduleManageActivity
import com.suda.yzune.wakeupschedule.settings.AdvancedSettingsActivity
import com.suda.yzune.wakeupschedule.settings.SettingItemAdapter
import com.suda.yzune.wakeupschedule.settings.TimeSettingsActivity
import com.suda.yzune.wakeupschedule.settings.items.*
import com.suda.yzune.wakeupschedule.utils.AppWidgetUtils
import com.suda.yzune.wakeupschedule.widget.colorpicker.ColorPickerFragment
import es.dmoral.toasty.Toasty
import splitties.activities.start
import splitties.dimensions.dip
import splitties.snackbar.longSnack

private const val TITLE_COLOR = 1
private const val COURSE_TEXT_COLOR = 2
private const val STROKE_COLOR = 3
private const val WIDGET_TITLE_COLOR = 4
private const val WIDGET_COURSE_TEXT_COLOR = 5
private const val WIDGET_STROKE_COLOR = 6

class ScheduleSettingsActivity : BaseListActivity(), ColorPickerFragment.ColorPickerDialogListener {

    override fun onColorSelected(dialogId: Int, color: Int) {
        when (dialogId) {
            TITLE_COLOR -> viewModel.table.textColor = color
            COURSE_TEXT_COLOR -> viewModel.table.courseTextColor = color
            STROKE_COLOR -> viewModel.table.strokeColor = color
            WIDGET_TITLE_COLOR -> viewModel.table.widgetTextColor = color
            WIDGET_COURSE_TEXT_COLOR -> viewModel.table.widgetCourseTextColor = color
            WIDGET_STROKE_COLOR -> viewModel.table.widgetStrokeColor = color
        }
    }

    override fun onSetupSubButton(tvButton: AppCompatTextView): AppCompatTextView? {
        val iconFont = ResourcesCompat.getFont(this, R.font.iconfont)
        tvButton.typeface = iconFont
        tvButton.textSize = 20f
        tvButton.text = getString(R.string.icon_heart)
        if (BuildConfig.CHANNEL == "google" || BuildConfig.CHANNEL == "huawei") {
            tvButton.setOnClickListener {
                val dialog = DonateFragment.newInstance()
                dialog.show(supportFragmentManager, "donateDialog")
            }
        } else {
            tvButton.setOnClickListener {
                start<DonateActivity>()
            }
        }
        return tvButton
    }

    private val viewModel by viewModels<ScheduleSettingsViewModel>()
    private val mAdapter = SettingItemAdapter()
    private val REQUEST_CODE_CHOOSE_BG = 23
    private val REQUEST_CODE_CHOOSE_TABLE = 21
    private val allItems = mutableListOf<BaseSettingItem>()
    private val showItems = mutableListOf<BaseSettingItem>()

    private val currentWeekItem by lazy(LazyThreadSafetyMode.NONE) {
        SeekBarItem("?????????", viewModel.getCurrentWeek(), 1, viewModel.table.maxWeek, "???", "???", keys = listOf("??????", "???", "??????", "??????", "??????", "??????"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        showSearch = true
        textWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showItems.clear()
                if (s.isNullOrBlank() || s.isEmpty()) {
                    showItems.addAll(allItems)
                } else {
                    showItems.add(CategoryItem("????????????", true))
                    showItems.addAll(allItems.filter {
                        val k = it.keyWords
                        k?.contains(s.toString()) ?: false
                    })
                }
                mRecyclerView.adapter?.notifyDataSetChanged()
                if (showItems.size == 1) {
                    mRecyclerView.longSnack("?????????????????????????????????????????????????????????????????????????????????????????????")
                }
            }
        }
        super.onCreate(savedInstanceState)
        viewModel.table = intent.extras!!.getParcelable<TableBean>("tableData") as TableBean

        //onAdapterCreated(mAdapter)

        onItemsCreated(allItems)
        showItems.addAll(allItems)
        mAdapter.data = showItems
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.itemAnimator?.changeDuration = 250
        mRecyclerView.adapter = mAdapter
        mAdapter.addChildClickViewIds(R.id.anko_check_box)
        mAdapter.setOnItemChildClickListener { _, view, position ->
            when (val item = showItems[position]) {
                is SwitchItem -> onSwitchItemCheckChange(item, view.findViewById<AppCompatCheckBox>(R.id.anko_check_box).isChecked)
            }
        }
        mAdapter.setOnItemClickListener { _, view, position ->
            when (val item = showItems[position]) {
                is HorizontalItem -> onHorizontalItemClick(item, position)
                is VerticalItem -> onVerticalItemClick(item)
                is SwitchItem -> view.findViewById<AppCompatCheckBox>(R.id.anko_check_box).performClick()
                is SeekBarItem -> onSeekBarItemClick(item, position)
            }
        }
        mAdapter.setOnItemLongClickListener { _, _, position ->
            when (val item = showItems[position]) {
                is VerticalItem -> onVerticalItemLongClick(item)
            }
            true
        }
        viewModel.termStartList = viewModel.table.startDate.split("-")
        viewModel.mYear = Integer.parseInt(viewModel.termStartList[0])
        viewModel.mMonth = Integer.parseInt(viewModel.termStartList[1])
        viewModel.mDay = Integer.parseInt(viewModel.termStartList[2])
        val settingItem = intent?.extras?.getString("settingItem")
        if (settingItem != null) {
            mRecyclerView.postDelayed({
                try {
                    val i = showItems.indexOfFirst {
                        it.title == settingItem
                    }
                    (mRecyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(i, dip(64))
                    when (showItems[i]) {
                        is HorizontalItem -> onHorizontalItemClick(showItems[i] as HorizontalItem, i)
                        is VerticalItem -> onVerticalItemClick(showItems[i] as VerticalItem)
                        is SeekBarItem -> onSeekBarItemClick(showItems[i] as SeekBarItem, i)
                    }
                } catch (e: Exception) {

                }
            }, 100)
        }
    }

    private fun onItemsCreated(items: MutableList<BaseSettingItem>) {
        items.add(CategoryItem("????????????", true))
        items.add(HorizontalItem("????????????", viewModel.table.tableName, listOf("??????", "??????", "???", "??????")))
        items.add(HorizontalItem("????????????", "??????????????????", listOf("??????")))
        items.add(HorizontalItem("??????????????????", viewModel.table.startDate, listOf("??????", "???", "??????", "??????", "??????", "??????")))
        items.add(currentWeekItem)
        items.add(HorizontalItem("?????????????????????", "", keys = listOf("??????", "???")))
        items.add(SeekBarItem("??????????????????", viewModel.table.nodes, 1, 30, "???", keys = listOf("??????", "??????", "???")))
        items.add(SeekBarItem("????????????", viewModel.table.maxWeek, 1, 30, "???", keys = listOf("??????", "???", "??????")))
        items.add(SwitchItem("????????????????????????", viewModel.table.sundayFirst, keys = listOf("??????", "?????????", "??????", "?????????", "???")))
        items.add(SwitchItem("????????????", viewModel.table.showSat, keys = listOf("??????", "??????", "?????????", "???")))
        items.add(SwitchItem("????????????", viewModel.table.showSun, keys = listOf("??????", "??????", "?????????", "???", "?????????", "??????")))

        items.add(CategoryItem("????????????", false))
        items.add(SwitchItem("??????????????????????????????", viewModel.table.showTime, keys = listOf("??????", "??????", "??????", "????????????")))
        items.add(VerticalItem("???????????????", "???????????????????????????~", keys = listOf("??????", "??????", "??????")))
        items.add(VerticalItem("??????????????????", "???????????????????????????\n???????????????????????????????????(??????????????)", keys = listOf("??????", "??????", "??????", "????????????")))
        items.add(VerticalItem("??????????????????", "???????????????????????????\n???????????????????????????????????(??????????????)", keys = listOf("??????", "??????", "??????", "????????????")))
        items.add(VerticalItem("??????????????????", "??????????????????????????????????????????????????????~", keys = listOf("??????", "??????", "????????????", "??????", "???")))
        items.add(SeekBarItem("??????????????????", viewModel.table.itemHeight, 32, 96, "dp", keys = listOf("??????", "??????", "????????????", "??????")))
        items.add(SeekBarItem("????????????????????????", viewModel.table.itemAlpha, 0, 100, "%", keys = listOf("??????", "??????", "????????????", "??????")))
        items.add(SeekBarItem("????????????????????????", viewModel.table.itemTextSize, 8, 16, "sp", keys = listOf("??????", "??????", "????????????")))
        items.add(SwitchItem("?????????????????????", viewModel.table.showOtherWeekCourse, keys = listOf("?????????")))

        items.add(CategoryItem("?????????????????????", false))
        items.add(SeekBarItem("?????????????????????", viewModel.table.widgetItemHeight, 32, 96, "dp", keys = listOf("??????", "??????", "????????????", "??????", "?????????", "???", "??????", "??????")))
        items.add(SeekBarItem("???????????????????????????", viewModel.table.widgetItemAlpha, 0, 100, "%", keys = listOf("??????", "??????", "????????????", "??????", "?????????", "???", "??????", "??????")))
        items.add(SeekBarItem("???????????????????????????", viewModel.table.widgetItemTextSize, 8, 16, "sp", keys = listOf("??????", "??????", "????????????", "?????????", "???", "??????", "??????")))
        items.add(VerticalItem("?????????????????????", "???????????????????????????\n??????????????????????????????????????????\n???????????????????????????????????(??????????????)", keys = listOf("??????", "??????", "??????", "????????????", "?????????", "???", "??????", "??????")))
        items.add(VerticalItem("?????????????????????", "?????????????????????????????????\n???????????????????????????????????(??????????????)", keys = listOf("??????", "??????", "??????", "????????????", "?????????", "???", "??????", "??????")))
        items.add(VerticalItem("???????????????????????????", "??????????????????????????????????????????????????????~", keys = listOf("??????", "??????", "????????????", "??????", "???", "?????????", "???", "??????", "??????")))

        items.add(CategoryItem("??????", false))
        when (BuildConfig.CHANNEL) {
            "google" -> items.add(VerticalItem("??????????????????????????????", "???????????????????????????????????????\n???????????????18862196504\n???????????????????????????~\n???????????????????????????(=????????=)o", keys = listOf("??????")))
            "huawei" -> items.add(VerticalItem("??????????????????????????????", "???????????????????????????~", keys = listOf("??????")))
            else -> items.add(VerticalItem("??????????????????", "???????????????????????????????????????(=????????=)o\n???????????????????????????~\n????????????????????????", keys = listOf("??????")))
        }

        items.add(VerticalItem("", "\n\n\n"))
    }

    private fun onSwitchItemCheckChange(item: SwitchItem, isChecked: Boolean) {
        when (item.title) {
            "????????????????????????" -> viewModel.table.sundayFirst = isChecked
            "????????????" -> viewModel.table.showSat = isChecked
            "????????????" -> viewModel.table.showSun = isChecked
            "??????????????????????????????" -> viewModel.table.showTime = isChecked
            "?????????????????????" -> viewModel.table.showOtherWeekCourse = isChecked
        }
        item.checked = isChecked
    }

    private fun onSeekBarItemClick(item: SeekBarItem, position: Int) {
        val dialog = MaterialAlertDialogBuilder(this)
                .setTitle(item.title)
                .setView(R.layout.dialog_edit_text)
                .setNegativeButton(R.string.cancel, null)
                .setPositiveButton(R.string.sure, null)
                .setCancelable(false)
                .create()
        dialog.show()
        val inputLayout = dialog.findViewById<TextInputLayout>(R.id.text_input_layout)
        val editText = dialog.findViewById<TextInputEditText>(R.id.edit_text)
        inputLayout?.helperText = "?????? ${item.min} ~ ${item.max}"
        if (item.prefix.isNotEmpty()) {
            inputLayout?.prefixText = item.prefix
        }
        inputLayout?.suffixText = item.unit
        editText?.inputType = InputType.TYPE_CLASS_NUMBER
        if (item.valueInt < item.min) {
            item.valueInt = item.min
        }
        if (item.valueInt > item.max) {
            item.valueInt = item.max
        }
        val valueStr = item.valueInt.toString()
        editText?.setText(valueStr)
        editText?.setSelection(valueStr.length)
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val value = editText?.text
            if (value.isNullOrBlank()) {
                inputLayout?.error = "?????????????????????>_<"
                return@setOnClickListener
            }
            val valueInt = try {
                value.toString().toInt()
            } catch (e: Exception) {
                inputLayout?.error = "????????????>_<"
                return@setOnClickListener
            }
            if (valueInt < item.min || valueInt > item.max) {
                inputLayout?.error = "???????????? ${item.min} ~ ${item.max}"
                return@setOnClickListener
            }
            when (item.title) {
                "??????????????????" -> viewModel.table.nodes = valueInt
                "????????????" -> {
                    currentWeekItem.max = valueInt
                    viewModel.table.maxWeek = valueInt
                }
                "?????????" -> {
                    viewModel.setCurrentWeek(valueInt)
                    item.valueInt = valueInt
                    (mAdapter.data[position - 1] as HorizontalItem).value = viewModel.table.startDate
                    mAdapter.notifyItemChanged(position - 1)
                    mAdapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
                "??????????????????" -> viewModel.table.itemHeight = valueInt
                "????????????????????????" -> viewModel.table.itemAlpha = valueInt
                "????????????????????????" -> viewModel.table.itemTextSize = valueInt
                "?????????????????????" -> viewModel.table.widgetItemHeight = valueInt
                "???????????????????????????" -> viewModel.table.widgetItemAlpha = valueInt
                "???????????????????????????" -> viewModel.table.widgetItemTextSize = valueInt
            }
            item.valueInt = valueInt
            mAdapter.notifyItemChanged(position)
            dialog.dismiss()
        }
    }

    private fun onHorizontalItemClick(item: HorizontalItem, position: Int) {
        when (item.title) {
            "????????????" -> {
                val dialog = MaterialAlertDialogBuilder(this)
                        .setTitle(R.string.setting_schedule_name)
                        .setView(R.layout.dialog_edit_text)
                        .setNegativeButton(R.string.cancel, null)
                        .setPositiveButton(R.string.sure, null)
                        .create()
                dialog.show()
                val inputLayout = dialog.findViewById<TextInputLayout>(R.id.text_input_layout)
                val editText = dialog.findViewById<TextInputEditText>(R.id.edit_text)
                editText?.setText(item.value)
                editText?.setSelection(item.value.length)
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    val value = editText?.text
                    if (value.isNullOrBlank()) {
                        inputLayout?.error = "?????????????????????>_<"
                        return@setOnClickListener
                    }
                    viewModel.table.tableName = value.toString()
                    item.value = value.toString()
                    mAdapter.notifyItemChanged(position)
                    dialog.dismiss()
                }
            }
            "??????????????????" -> {
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                    viewModel.mYear = year
                    viewModel.mMonth = monthOfYear + 1
                    viewModel.mDay = dayOfMonth
                    val mDate = "${viewModel.mYear}-${viewModel.mMonth}-${viewModel.mDay}"
                    item.value = mDate
                    viewModel.table.startDate = mDate
                    currentWeekItem.valueInt = viewModel.getCurrentWeek()
                    mAdapter.notifyItemChanged(position)
                    mAdapter.notifyItemChanged(position + 1)
                }, viewModel.mYear, viewModel.mMonth - 1, viewModel.mDay).show()
                if (viewModel.table.sundayFirst) {
                    Toasty.success(this, "????????????????????????????????????????????????", Toast.LENGTH_LONG).show()
                } else {
                    Toasty.success(this, "????????????????????????????????????????????????", Toast.LENGTH_LONG).show()
                }
            }
            "????????????" -> {
                startActivityForResult(Intent(this, TimeSettingsActivity::class.java).apply {
                    putExtra("selectedId", viewModel.table.timeTable)
                }, REQUEST_CODE_CHOOSE_TABLE)
            }
            "?????????????????????" -> {
                start<ScheduleManageActivity> {
                    putExtra("selectedTable", TableSelectBean(
                            id = viewModel.table.id,
                            background = viewModel.table.background,
                            tableName = viewModel.table.tableName,
                            maxWeek = viewModel.table.maxWeek,
                            nodes = viewModel.table.nodes,
                            type = viewModel.table.type
                    ))
                }
            }
        }
    }

    private fun onVerticalItemClick(item: VerticalItem) {
        when (item.title) {
            "???????????????" -> {
                val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
                    addCategory(Intent.CATEGORY_OPENABLE)
                    type = "image/*"
                }
                try {
                    startActivityForResult(intent, REQUEST_CODE_CHOOSE_BG)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }
            "??????????????????" -> {
                buildColorPickerDialogBuilder(viewModel.table.textColor, TITLE_COLOR)
            }
            "??????????????????" -> {
                buildColorPickerDialogBuilder(viewModel.table.courseTextColor, COURSE_TEXT_COLOR)
            }
            "??????????????????" -> {
                buildColorPickerDialogBuilder(viewModel.table.strokeColor, STROKE_COLOR)
            }
            "?????????????????????" -> {
                buildColorPickerDialogBuilder(viewModel.table.widgetTextColor, WIDGET_TITLE_COLOR)
            }
            "?????????????????????" -> {
                buildColorPickerDialogBuilder(viewModel.table.widgetCourseTextColor, WIDGET_COURSE_TEXT_COLOR)
            }
            "???????????????????????????" -> {
                buildColorPickerDialogBuilder(viewModel.table.widgetStrokeColor, WIDGET_STROKE_COLOR)
            }
            "??????????????????" -> {
                start<AdvancedSettingsActivity>()
            }
            "??????????????????????????????" -> {
                start<AdvancedSettingsActivity>()
            }
        }
    }

    private fun onVerticalItemLongClick(item: VerticalItem): Boolean {
        return when (item.title) {
            "???????????????" -> {
                viewModel.table.background = ""
                Toasty.success(applicationContext, "????????????????????????~").show()
                true
            }
            else -> false
        }
    }

    private fun buildColorPickerDialogBuilder(color: Int, id: Int) {
        ColorPickerFragment.newBuilder()
                .setShowAlphaSlider(true)
                .setColor(color)
                .setDialogId(id)
                .show(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_CHOOSE_BG && resultCode == RESULT_OK) {
            //viewModel.table.background = Matisse.obtainResult(data)[0].toString()
            val uri = data?.data
            if (uri != null) {
                viewModel.table.background = uri.toString()
            }
        }
        if (requestCode == REQUEST_CODE_CHOOSE_TABLE && resultCode == RESULT_OK) {
            viewModel.table.timeTable = data!!.getIntExtra("selectedId", 1)
        }
    }

    override fun onBackPressed() {
        launch {
            AppWidgetUtils.updateWidget(applicationContext)
            viewModel.saveSettings()
            val list = viewModel.getScheduleWidgetIds()
            val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
            list.forEach {
                when (it.detailType) {
                    0 -> {
                        if (it.info == viewModel.table.id.toString()) {
                            AppWidgetUtils.refreshScheduleWidget(applicationContext, appWidgetManager, it.id, viewModel.table)
                        }
                    }
                    1 -> AppWidgetUtils.refreshTodayWidget(applicationContext, appWidgetManager, it.id, viewModel.table, false)
                }
            }
            setResult(RESULT_OK)
            finish()
        }
    }
}
