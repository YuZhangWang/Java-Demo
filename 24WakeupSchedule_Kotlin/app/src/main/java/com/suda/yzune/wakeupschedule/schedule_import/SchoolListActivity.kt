package com.suda.yzune.wakeupschedule.schedule_import

import android.app.Activity
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.edit
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bigkoo.quicksidebar.listener.OnQuickSideBarTouchListener
import com.google.gson.Gson
import com.suda.yzune.wakeupschedule.AppDatabase
import com.suda.yzune.wakeupschedule.R
import com.suda.yzune.wakeupschedule.base_view.BaseTitleActivity
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_BNUZ
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_CF
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_HELP
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_HNIU
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_HNUST
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_JNU
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_LOGIN
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_PKU
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_QZ
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_QZ_BR
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_QZ_CRAZY
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_QZ_OLD
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_QZ_WITH_NODE
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_URP
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_URP_NEW
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_ZF
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_ZF_1
import com.suda.yzune.wakeupschedule.schedule_import.Common.TYPE_ZF_NEW
import com.suda.yzune.wakeupschedule.schedule_import.bean.SchoolInfo
import com.suda.yzune.wakeupschedule.utils.Const
import com.suda.yzune.wakeupschedule.utils.Utils
import com.suda.yzune.wakeupschedule.utils.getPrefer
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_school_list.*
import splitties.activities.start
import splitties.dimensions.dip
import splitties.resources.color
import splitties.resources.styledColor
import splitties.snackbar.action
import splitties.snackbar.longSnack

class SchoolListActivity : BaseTitleActivity(), OnQuickSideBarTouchListener {

    private val letters = HashMap<String, Int>()
    private val showList = arrayListOf<SchoolInfo>()
    private val schools = arrayListOf<SchoolInfo>()
    private lateinit var searchView: AppCompatEditText
    private var fromLocal = false

    override val layoutId: Int
        get() = R.layout.activity_school_list

    override fun onSetupSubButton(tvButton: AppCompatTextView): AppCompatTextView? {
        tvButton.text = "????????????"
        tvButton.setOnClickListener {
            start<LoginWebActivity> {
                putExtra("import_type", "apply")
            }
            finish()
        }
        return tvButton
    }

    override fun createTitleBar() = LinearLayoutCompat(this).apply {
        orientation = LinearLayoutCompat.VERTICAL
        setBackgroundColor(styledColor(R.attr.colorSurface))
        addView(LinearLayoutCompat(context).apply {
            setPadding(0, getStatusBarHeight(), 0, 0)
            setBackgroundColor(styledColor(R.attr.colorSurface))
            val outValue = TypedValue()
            context.theme.resolveAttribute(R.attr.selectableItemBackgroundBorderless, outValue, true)

            addView(AppCompatImageButton(context).apply {
                setImageResource(R.drawable.ic_back)
                setBackgroundResource(outValue.resourceId)
                setPadding(dip(8))
                setColorFilter(styledColor(R.attr.colorOnBackground))
                setOnClickListener {
                    onBackPressed()
                }
            }, LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, dip(48)))

            mainTitle = AppCompatTextView(context).apply {
                text = title
                gravity = Gravity.CENTER_VERTICAL
                textSize = 16f
                typeface = Typeface.DEFAULT_BOLD
            }

            addView(mainTitle, LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, dip(48)).apply {
                weight = 1f
            })

            searchView = AppCompatEditText(context).apply {
                hint = "???????????????"
                textSize = 16f
                background = null
                gravity = Gravity.CENTER_VERTICAL
                visibility = View.GONE
                setLines(1)
                setSingleLine()
                imeOptions = EditorInfo.IME_ACTION_SEARCH
                addTextChangedListener(object : TextWatcher {
                    override fun afterTextChanged(s: Editable?) {}

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                        showList.clear()
                        if (s.isNullOrBlank() || s.isEmpty()) {
                            showList.addAll(schools)
                        } else {
                            showList.addAll(schools.filter {
                                it.name.contains(s.toString())
                            })
                        }
                        recyclerView.adapter?.notifyDataSetChanged()
                        if (showList.isEmpty()) {
                            longSnack("???????????????????????????") {
                                action("????????????") {
                                    start<LoginWebActivity> {
                                        putExtra("import_type", "apply")
                                    }
                                }
                            }
                        }
                    }

                })
            }

            addView(searchView, LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, dip(48)).apply {
                weight = 1f
            })

            val iconFont = ResourcesCompat.getFont(context, R.font.iconfont)
            addView(AppCompatTextView(context).apply {
                textSize = 20f
                typeface = iconFont
                text = "\uE6D4"
                gravity = Gravity.CENTER
                setBackgroundResource(outValue.resourceId)
                setOnClickListener {
                    when (searchView.visibility) {
                        View.GONE -> {
                            mainTitle.visibility = View.GONE
                            searchView.visibility = View.VISIBLE
                            setTextColor(color(R.color.colorAccent))
                            searchView.isFocusable = true
                            searchView.isFocusableInTouchMode = true
                            searchView.requestFocus()
                            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                            inputMethodManager.showSoftInput(searchView, 0)
                        }
                    }
                }
            }, LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.WRAP_CONTENT, dip(48)).apply {
                marginEnd = dip(24)
            })
        }, LinearLayoutCompat.LayoutParams(LinearLayoutCompat.LayoutParams.MATCH_PARENT, LinearLayoutCompat.LayoutParams.WRAP_CONTENT))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fromLocal = intent.getBooleanExtra("fromLocal", false)
        quickSideBarView.setOnQuickSideBarTouchListener(this)
        initSchoolList()
    }

    private fun initSchoolList() {
        val dataBase = AppDatabase.getDatabase(application)
        val tableDao = dataBase.tableDao()
        val gson = Gson()
        schools.apply {
            add(SchoolInfo("???", "?????????????????????????????????", "https://support.qq.com/embed/97617/faqs/59901", TYPE_HELP))
            getImportSchoolBean()?.let {
                it.sortKey = "???"
                add(it)
            }
            add(SchoolInfo("???", "??? URP ??????", "", TYPE_URP_NEW))
            add(SchoolInfo("???", "URP ??????", "", TYPE_URP))
            add(SchoolInfo("???", "???????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("???", "????????????", "", TYPE_ZF))
            add(SchoolInfo("???", "????????????", "", TYPE_QZ))
            add(SchoolInfo("???", "?????????????????? IE ????????????", "", TYPE_QZ_OLD))
            add(SchoolInfo("A", "????????????????????????", "http://teach.aiit.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("A", "??????????????????", "http://newjwxt.ahau.edu.cn/jwglxt", TYPE_ZF_NEW))
            add(SchoolInfo("A", "????????????", "http://xk2.ahu.cn/default2.aspx", TYPE_ZF))
            add(SchoolInfo("A", "??????????????????", "http://jwxt.ahut.edu.cn/jsxsd/", TYPE_QZ))
            add(SchoolInfo("A", "??????????????????", "http://219.231.0.156/", TYPE_ZF_NEW))
            add(SchoolInfo("A", "??????????????????", "", TYPE_URP_NEW))
            add(SchoolInfo("B", "????????????", "http://jwgl.bdu.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("B", "????????????????????????", "http://jwgl.bistu.edu.cn/", TYPE_ZF))
            add(SchoolInfo("B", "??????????????????", "http://jwglxt.buct.edu.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("B", "????????????", "http://elective.pku.edu.cn", TYPE_PKU))
            add(SchoolInfo("B", "??????????????????", "http://gdjwgl.bjut.edu.cn/", TYPE_ZF))
            add(SchoolInfo("B", "??????????????????????????????", "http://es.bnuz.edu.cn/", TYPE_BNUZ))
            add(SchoolInfo("B", "??????????????????", "http://newjwxt.bjfu.edu.cn/", TYPE_QZ_BR))
            add(SchoolInfo("B", "??????????????????", "http://jwms.bit.edu.cn/", TYPE_QZ_WITH_NODE))
            add(SchoolInfo("B", "??????????????????????????????", "http://e.zhbit.com/jsxsd/", TYPE_QZ_WITH_NODE))
            add(SchoolInfo("B", "??????????????????", "", TYPE_ZF))
            add(SchoolInfo("B", "??????????????????", "https://jwxt.bupt.edu.cn/", TYPE_URP))
            add(SchoolInfo("B", "????????????", "http://jw.bhu.edu.cn/", TYPE_URP))
            add(SchoolInfo("B", "???????????????", "http://jwgl.bzmc.edu.cn/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("C", "??????????????????????????????", "http://jwc.czmec.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("C", "????????????????????????????????????", "http://110.189.108.15/", TYPE_ZF))
            add(SchoolInfo("C", "??????????????????", "http://jwgl.sanxiau.edu.cn/", TYPE_ZF))
            add(SchoolInfo("C", "??????????????????", "http://jwgl.cqjtu.edu.cn/jsxsd/", TYPE_QZ))
            add(SchoolInfo("C", "????????????????????????", "", TYPE_ZF_1))
            add(SchoolInfo("C", "??????????????????????????????", "", TYPE_QZ_CRAZY))
            add(SchoolInfo("C", "??????????????????????????????", "http://222.179.134.225:81/", TYPE_ZF))
            add(SchoolInfo("C", "????????????", "http://cdjwc.ccu.edu.cn/jsxsd/", TYPE_QZ_BR))
            add(SchoolInfo("C", "???????????????", "http://jiaowu.csmu.edu.cn:8099/jsxsd/", TYPE_QZ_WITH_NODE))
            add(SchoolInfo("C", "??????????????????", "http://xk.csust.edu.cn/", TYPE_QZ_BR))
            add(SchoolInfo("D", "??????????????????", "http://jwcnew.nefu.edu.cn/dblydx_jsxsd/", TYPE_QZ))
            add(SchoolInfo("D", "??????????????????", "http://202.199.165.159/", TYPE_URP))
            add(SchoolInfo("D", "??????????????????", "http://jwgl.nepu.edu.cn/", TYPE_HNUST))
            add(SchoolInfo("D", "??????????????????", "", TYPE_QZ))
            add(SchoolInfo("D", "?????????????????????", "http://cas.dlufl.edu.cn/cas/", TYPE_QZ))
            add(SchoolInfo("D", "????????????", "http://202.199.155.33/default2.aspx", TYPE_ZF))
            add(SchoolInfo("D", "?????????????????????????????????????????????", "http://www.caie.org/page_556.shtml", TYPE_ZF))
            add(SchoolInfo("D", "????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("D", "??????????????????????????????", "http://jwgln.zsc.edu.cn/jsxsd/", TYPE_QZ))
            add(SchoolInfo("F", "????????????????????????", "http://100.fosu.edu.cn/", TYPE_QZ_CRAZY))
            add(SchoolInfo("F", "??????????????????", "http://jwgl.fafu.edu.cn", TYPE_ZF_1))
            add(SchoolInfo("F", "??????????????????????????????", "http://jsxyjwgl.fafu.edu.cn/", TYPE_ZF))
            add(SchoolInfo("F", "??????????????????", "https://jwxtwx.fjut.edu.cn/jwglxt/", TYPE_ZF_NEW))
            add(SchoolInfo("F", "??????????????????", "http://jwglxt.fjnu.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("G", "????????????????????????", "http://jxgl.gdufs.edu.cn/jsxsd/", TYPE_QZ_WITH_NODE))
            add(SchoolInfo("G", "??????????????????", "http://jxfw.gdut.edu.cn/", TYPE_CF))
            add(SchoolInfo("G", "??????????????????", "http://210.38.137.126:8016/default2.aspx", TYPE_ZF))
            add(SchoolInfo("G", "????????????????????????????????????", "http://113.107.254.7/", TYPE_ZF))
            add(SchoolInfo("G", "??????????????????????????????", "", TYPE_ZF_1))
            add(SchoolInfo("G", "??????????????????", "http://jwxt.gdufe.edu.cn/", TYPE_QZ))
            add(SchoolInfo("G", "??????????????????", "http://jwxt.gduf.edu.cn/", TYPE_QZ_BR))
            add(SchoolInfo("G", "??????????????????", "", TYPE_QZ_WITH_NODE))
            add(SchoolInfo("G", "????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("G", "????????????", "http://jwxt2018.gxu.edu.cn/jwglxt/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("G", "??????????????????????????????", "http://210.36.24.21:9017/jwglxt/xtgl", TYPE_ZF_NEW))
            add(SchoolInfo("G", "??????????????????", "http://172.16.130.25/dean/student/login", TYPE_QZ))
            add(SchoolInfo("G", "????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("G", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????", "https://inquiry.ecust.edu.cn/jsxsd/", TYPE_QZ))
            add(SchoolInfo("H", "??????????????????", "http://jwgl.hzau.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????", "http://one.ccnu.edu.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????", "", TYPE_LOGIN))
            add(SchoolInfo("H", "??????????????????????????????", "http://202.204.74.178/", TYPE_ZF))
            add(SchoolInfo("H", "??????????????????", "http://202.116.160.170/default2.aspx", TYPE_ZF))
            add(SchoolInfo("H", "??????????????????", "http://xsjw2018.scuteo.com", TYPE_ZF_NEW))
            add(SchoolInfo("H", "?????????????????????", "http://jwxsd.hrbcu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("H", "?????????????????????", "", TYPE_QZ_CRAZY))
            add(SchoolInfo("H", "???????????????", "http://edu.hmc.edu.cn/", TYPE_ZF))
            add(SchoolInfo("H", "????????????????????????", "http://jxgl.hdu.edu.cn/", TYPE_ZF))
            add(SchoolInfo("H", "????????????", "http://zhjw.hbu.edu.cn/", TYPE_URP))
            add(SchoolInfo("H", "??????????????????", "http://219.148.85.172:9111/login", TYPE_URP_NEW))
            add(SchoolInfo("H", "??????????????????", "http://jwgl.hebtu.edu.cn/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("H", "????????????????????????", "http://jwxt.helc.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("H", "????????????????????????", "http://jw.hebuee.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("H", "????????????????????????", "http://121.22.25.47/", TYPE_ZF))
            add(SchoolInfo("H", "??????????????????", "http://222.30.218.44/default2.aspx", TYPE_ZF))
            add(SchoolInfo("H", "??????????????????", "", TYPE_QZ_CRAZY))
            add(SchoolInfo("H", "??????????????????", "http://125.219.48.18/", TYPE_ZF))
            add(SchoolInfo("H", "??????????????????", "", TYPE_URP))
            add(SchoolInfo("H", "????????????????????????", "http://xk.huel.edu.cn/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("H", "????????????", "http://202.119.113.135/", TYPE_URP))
            add(SchoolInfo("H", "????????????", "http://jxgl.hainu.edu.cn/", TYPE_QZ_WITH_NODE))
            add(SchoolInfo("H", "??????????????????", "http://210.37.0.16/", TYPE_ZF))
            add(SchoolInfo("H", "??????????????????", "http://211.70.176.173/jwglxt/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("H", "?????????????????????", "http://jwxt.hbtcm.edu.cn/jwglxt/xtgl", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????", "http://jw.hbmu.edu.cn", TYPE_CF))
            add(SchoolInfo("H", "?????????????????????????????????", "http://jwglxt.hbeutc.cn:20000/jwglxt/xtgl", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????", "http://jwxt.hbnu.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????????????????", "http://my.hniu.cn/jwweb/ZNPK/KBFB_ClassSel.aspx", TYPE_HNIU))
            add(SchoolInfo("H", "??????????????????", "http://jwc.hunau.edu.cn/xsxk/", TYPE_ZF))
            add(SchoolInfo("H", "???????????????", "http://jwgl.hnuc.edu.cn/", TYPE_QZ))
            add(SchoolInfo("H", "??????????????????", "http://58.47.143.9:2045/zfca/login", TYPE_ZF))
            add(SchoolInfo("H", "??????????????????", "http://218.75.197.123:83/", TYPE_QZ))
            add(SchoolInfo("H", "??????????????????", "http://jwgl.hnuc.edu.cn/", TYPE_QZ))
            add(SchoolInfo("H", "???????????????", "http://jwgl.hnit.edu.cn/", TYPE_QZ_OLD))
            add(SchoolInfo("H", "??????????????????", "http://bkjw.hnist.cn/login", TYPE_URP))
            add(SchoolInfo("H", "??????????????????", "http://kdjw.hnust.cn:8080/kdjw", TYPE_HNUST))
            add(SchoolInfo("H", "??????????????????????????????", "http://xxjw.hnust.cn:8080/xxjw/", TYPE_HNUST))
            add(SchoolInfo("H", "????????????", "http://jwglxt.hzu.gx.cn/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("H", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("H", "????????????????????????", "", TYPE_ZF))
            add(SchoolInfo("J", "????????????", "", TYPE_LOGIN))
            add(SchoolInfo("J", "??????????????????", "http://jwxt.jlnu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("J", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("J", "????????????", "http://jwxt.jsu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("J", "????????????????????????", "http://jwzx.zjxu.edu.cn/jwglxt/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("J", "??????????????????????????????", "http://tyjw.tmu.edu.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("J", "??????????????????", "http://sdjw.jsnu.edu.cn/", TYPE_QZ_WITH_NODE))
            add(SchoolInfo("J", "??????????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("J", "??????????????????", "http://jwgl.just.edu.cn:8080/jsxsd/", TYPE_QZ))
            add(SchoolInfo("J", "?????????????????????", "http://jwxt.jxutcm.edu.cn/jwglxt/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("J", "?????????????????????????????????", "http://223.83.249.67:8080/jsxsd/", TYPE_QZ_BR))
            add(SchoolInfo("J", "????????????", "https://jwxt.jnu.edu.cn/", TYPE_JNU))
            add(SchoolInfo("J", "????????????", "http://jwgl4.ujn.edu.cn/jwglxt", TYPE_ZF_NEW))
            add(SchoolInfo("J", "??????????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("J", "??????????????????", "http://jwgl.jzmu.edu.cn/jsxsd/", TYPE_QZ))
            add(SchoolInfo("L", "????????????", "http://jwxt.lyu.edu.cn/jxd/", TYPE_QZ))
            add(SchoolInfo("L", "??????????????????", "http://jwxt.lnut.edu.cn/default2.aspx", TYPE_ZF))
            add(SchoolInfo("L", "??????????????????????????????", "http://jwgl.lnjdp.com/", TYPE_ZF_NEW))
            add(SchoolInfo("M", "????????????????????????", "http://jwc.mmvtc.cn/", TYPE_ZF_1))
            add(SchoolInfo("M", "??????????????????", "http://222.205.160.107/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("N", "???????????????", "http://jwxt.imu.edu.cn/login", TYPE_URP_NEW))
            add(SchoolInfo("N", "?????????????????????", "", TYPE_QZ))
            add(SchoolInfo("N", "?????????????????????", "http://stuzhjw.imust.edu.cn/login", TYPE_URP_NEW))
            add(SchoolInfo("N", "???????????????????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("N", "????????????????????????", "http://jw.ncc.edu.cn/jwglxt/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("N", "??????????????????", "https://jwgl.njtech.edu.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("N", "??????????????????????????????", "http://222.192.5.246/", TYPE_ZF_NEW))
            add(SchoolInfo("N", "??????????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("N", "??????????????????", "http://202.119.81.112:8080/", TYPE_QZ))
            add(SchoolInfo("N", "??????????????????", "http://172.16.130.25/dean/student/login", TYPE_QZ))
            add(SchoolInfo("N", "????????????????????????", "http://jwxt.ncvt.net:8088/jwglxt/", TYPE_ZF_NEW))
            add(SchoolInfo("N", "??????????????????", "http://zhjw.smu.edu.cn/", TYPE_CF))
            add(SchoolInfo("N", "??????????????????", "http://jwxt.sustc.edu.cn/jsxsd", TYPE_QZ))
            add(SchoolInfo("N", "????????????", "http://jwc104.ncu.edu.cn:8081/jsxsd/", TYPE_QZ))
            add(SchoolInfo("N", "??????????????????", "", TYPE_QZ))
            add(SchoolInfo("N", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("Q", "????????????", "", TYPE_LOGIN))
            add(SchoolInfo("Q", "??????????????????", "", TYPE_QZ_BR))
            add(SchoolInfo("Q", "??????????????????", "http://jwgl.qdbhu.edu.cn/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("Q", "??????????????????", "https://jw.qust.edu.cn/jwglxt.htm", TYPE_ZF_NEW))
            add(SchoolInfo("Q", "??????????????????", "http://jwxt.qlu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("Q", "??????????????????", "", TYPE_URP_NEW))
            add(SchoolInfo("Q", "??????????????????", "", TYPE_URP))
            add(SchoolInfo("S", "????????????", "http://jw.sju.edu.cn/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("S", "????????????", "", TYPE_LOGIN))
            add(SchoolInfo("S", "??????????????????", "https://urp.shou.edu.cn/login", TYPE_URP_NEW))
            add(SchoolInfo("S", "????????????????????????", "http://jwweb.scujcc.cn/", TYPE_ZF))
            add(SchoolInfo("S", "??????????????????", "", TYPE_QZ))
            add(SchoolInfo("S", "?????????????????????", "http://61.139.105.138/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("S", "??????????????????", "http://xjw.sdau.edu.cn/jwglxt/", TYPE_ZF_NEW))
            add(SchoolInfo("S", "????????????????????????", "https://portal.wh.sdu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("S", "????????????????????????", "", TYPE_QZ))
            add(SchoolInfo("S", "??????????????????", "http://www.bkjw.sdnu.edu.cn", TYPE_ZF))
            add(SchoolInfo("S", "??????????????????", "http://114.214.79.176/jwglxt/", TYPE_ZF_NEW))
            add(SchoolInfo("S", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("S", "??????????????????", "http://jwgl.sdust.edu.cn/", TYPE_QZ))
            add(SchoolInfo("S", "??????????????????", "", TYPE_QZ))
            add(SchoolInfo("S", "????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("S", "??????????????????", "http://xsjwxt.sxau.edu.cn:7873/login", TYPE_URP_NEW))
            add(SchoolInfo("S", "????????????????????????", "http://211.82.48.36/login", TYPE_URP_NEW))
            add(SchoolInfo("S", "??????????????????", "http://awcwea.com/jwgl.sie.edu.cn/jwgl/", TYPE_QZ))
            add(SchoolInfo("S", "??????????????????", "http://210.30.208.140/", TYPE_ZF))
            add(SchoolInfo("S", "???????????????", "http://jwgl.sjzc.edu.cn/jwglxt/", TYPE_ZF_NEW))
            add(SchoolInfo("S", "??????????????????", "http://jw.usx.edu.cn/", TYPE_ZF))
            add(SchoolInfo("S", "??????????????????????????????", "http://www.ypc.edu.cn/jwgl.htm", TYPE_ZF))
            add(SchoolInfo("S", "??????????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("S", "????????????", "", TYPE_LOGIN))
            add(SchoolInfo("S", "??????????????????", "http://jw.usts.edu.cn/default2.aspx", TYPE_ZF))
            add(SchoolInfo("S", "??????????????????????????????", "http://tpjw.usts.edu.cn/default2.aspx", TYPE_ZF))
            add(SchoolInfo("S", "????????????", "http://jwc.sgu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("T", "?????????????????????", "http://jiaowu.tjutcm.edu.cn/jsxsd/", TYPE_QZ))
            add(SchoolInfo("T", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("T", "??????????????????", "http://tyjw.tmu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("T", "??????????????????", "http://jwpt.tjpu.edu.cn/", TYPE_URP))
            add(SchoolInfo("W", "????????????", "http://jxgl.wyu.edu.cn/", TYPE_CF))
            add(SchoolInfo("W", "??????????????????", "", TYPE_QZ))
            add(SchoolInfo("W", "??????????????????", "http://jwcnew.thxy.org/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("W", "??????????????????", "http://syjw.wsyu.edu.cn/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("W", "??????????????????", "http://221.232.159.27/", TYPE_ZF))
            add(SchoolInfo("W", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("W", "??????????????????", "http://jwglxt.whpu.edu.cn/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("W", "??????????????????", "http://jwxt.wmu.edu.cn", TYPE_ZF_NEW))
            add(SchoolInfo("W", "??????????????????", "http://218.195.46.9", TYPE_ZF))
            add(SchoolInfo("W", "????????????", "http://210.44.64.154/", TYPE_ZF))
            add(SchoolInfo("W", "??????????????????", "http://jwgl.sdwfvc.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("W", "????????????", "", TYPE_QZ))
            add(SchoolInfo("X", "??????????????????", "http://jwc.xynu.edu.cn/jxzhxxfwpt.htm", TYPE_ZF_NEW))
            add(SchoolInfo("X", "???????????????", "http://jwxt.xit.edu.cn/default2.aspx", TYPE_ZF))
            add(SchoolInfo("X", "??????????????????", "http://jw.xmut.edu.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("X", "??????????????????", "http://222.193.95.102/", TYPE_ZF_NEW))
            add(SchoolInfo("X", "????????????????????????????????????", "http://222.187.124.16/", TYPE_ZF))
            add(SchoolInfo("X", "????????????", "http://jwxt.xtu.edu.cn/jsxsd/", TYPE_QZ))
            add(SchoolInfo("X", "??????????????????", "", TYPE_LOGIN))
            add(SchoolInfo("X", "????????????", "http://jwc.xhu.edu.cn/", TYPE_ZF))
            add(SchoolInfo("X", "????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("X", "??????????????????", "http://njwxt.swupl.edu.cn/jwglxt/xtgl", TYPE_ZF_NEW))
            add(SchoolInfo("X", "??????????????????", "http://jwxt.swun.edu.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("X", "??????????????????", "http://jwxt.swpu.edu.cn/", TYPE_URP))
            add(SchoolInfo("X", "??????????????????", "http://jw.xaiu.edu.cn/", TYPE_ZF))
            add(SchoolInfo("X", "????????????????????????", "http://xk.xauat.edu.cn/default2.aspx#a", TYPE_ZF))
            add(SchoolInfo("X", "??????????????????", "http://202.200.112.200/", TYPE_ZF))
            add(SchoolInfo("X", "??????????????????", "http://59.74.168.16:8989/", TYPE_ZF))
            add(SchoolInfo("X", "??????????????????", "http://www.zfjw.xupt.edu.cn/jwglxt/", TYPE_ZF_NEW))
            add(SchoolInfo("X", "????????????", "https://jwxt.xcc.edu.cn/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("Y", "??????????????????", "http://202.203.194.2/", TYPE_ZF))
            add(SchoolInfo("Y", "????????????", "http://jwglxt.yau.edu.cn/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("Y", "????????????", "http://xk.jwc.ytu.edu.cn/", TYPE_URP_NEW))
            add(SchoolInfo("Z", "????????????", "https://csujwc.its.csu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("Z", "????????????????????????", "http://jwgl.csuft.edu.cn/", TYPE_QZ))
            add(SchoolInfo("Z", "????????????????????????", "", TYPE_QZ))
            add(SchoolInfo("Z", "??????????????????", "http://urpjw.cau.edu.cn/login", TYPE_URP_NEW))
            add(SchoolInfo("Z", "??????????????????", "http://jw.cmu.edu.cn/jwglxt/xtgl/login_slogin.html", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????????????????", "http://urp.cup.edu.cn/login", TYPE_URP_NEW))
            add(SchoolInfo("Z", "??????????????????", "http://jwxt.cumt.edu.cn/jwglxt/", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????????????????", "http://xhjw.cumt.edu.cn:8080/jwglxt/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????", "http://jwgl.cpu.edu.cn/", TYPE_QZ))
            add(SchoolInfo("Z", "??????????????????", "http://jwxt.zwu.edu.cn/", TYPE_ZF_1))
            add(SchoolInfo("Z", "??????????????????", "http://115.236.84.158/xtgl", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????", "http://www.gdjw.zjut.edu.cn/", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????????????????", "http://jwgl.zzjc.edu.cn/default2.aspx", TYPE_ZF))
            add(SchoolInfo("Z", "??????????????????", "http://124.160.64.163/jwglxt/xtgl/", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????????????????", "", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "??????????????????", "http://fzjh.zufe.edu.cn/jwglxt", TYPE_ZF_NEW))
            add(SchoolInfo("Z", "?????????????????????????????????", "http://218.198.176.111/default2.aspx", TYPE_ZF))
            add(SchoolInfo("Z", "??????????????????????????????", "http://202.196.166.138/", TYPE_ZF))
        }

//        schools.sortWith(compareBy({ it.sortKey }, { it.name }))
//
//        schools.add(0, SchoolInfo("???", "URP ??????"))
//        schools.add(0, SchoolInfo("???", "?????????????????? IE ????????????"))
//        schools.add(0, SchoolInfo("???", "????????????"))
//        schools.add(0, SchoolInfo("???", "???????????????"))
//        schools.add(0, SchoolInfo("???", "????????????"))

        val layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        showList.addAll(schools)
        val adapter = SchoolImportListAdapter(R.layout.item_apply_info, showList)
        adapter.setOnItemClickListener { _, _, position ->
            if (showList[position].type == TYPE_HELP) {
                Utils.openUrl(this, showList[position].url)
                return@setOnItemClickListener
            }
            if (fromLocal) {
                setResult(Activity.RESULT_OK, Intent().apply { putExtra("type", showList[position].type) })
                finish()
            } else {
                launch {
                    if (showList[position].type == Common.TYPE_MAINTAIN) {
                        Toasty.info(this@SchoolListActivity, "??????????????????").show()
                        return@launch
                    }
                    getPrefer().edit {
                        putString(Const.KEY_IMPORT_SCHOOL, gson.toJson(showList[position]))
                    }
                    val tableId = tableDao.getDefaultTableId()
                    startActivityForResult(Intent(this@SchoolListActivity, LoginWebActivity::class.java).apply {
                        putExtra("school_name", showList[position].name)
                        putExtra("import_type", showList[position].type)
                        putExtra("tableId", tableId)
                        putExtra("url", showList[position].url)
                    }, Const.REQUEST_CODE_IMPORT)
                }
            }
        }

        val customLetters = arrayListOf<String>()

        for ((position, school) in schools.withIndex()) {
            val letter = school.sortKey
            //??????????????????key??????????????????????????????
            if (!letters.containsKey(letter)) {
                letters[letter] = position
                customLetters.add(letter)
            }
        }

        quickSideBarView.letters = customLetters
        recyclerView.adapter = adapter

        val headersDecor = StickyRecyclerHeadersDecoration(adapter)
        recyclerView.addItemDecoration(headersDecor)
    }

    private fun getImportSchoolBean(): SchoolInfo? {
        val json = getPrefer().getString(Const.KEY_IMPORT_SCHOOL, null)
                ?: return null
        val gson = Gson()
        val res = gson.fromJson<SchoolInfo>(json, SchoolInfo::class.java)
        if (!res.type.isNullOrEmpty()) {
            return gson.fromJson<SchoolInfo>(json, SchoolInfo::class.java)
        }
        return null
    }

    override fun onLetterTouching(touching: Boolean) {
        quickSideBarTipsView.visibility = if (touching) View.VISIBLE else View.INVISIBLE
    }

    override fun onLetterChanged(letter: String, position: Int, y: Float) {
        quickSideBarTipsView.setText(letter, position, y)
        if (letters.containsKey(letter)) {
            (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(letters[letter]!!, 0)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == Const.REQUEST_CODE_IMPORT) {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }
}
