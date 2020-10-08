package mish.mish.assefa.com.hisab

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.actvity_mainn.*
import mish.mish.assefa.com.hisab.R.*
import mish.mish.assefa.com.hisab.data.Controller
import mish.mish.assefa.com.hisab.data.food.Food
import mish.mish.assefa.com.hisab.data.food.FoodTypes
import mish.mish.assefa.com.hisab.data.meal.Meal
import mish.mish.assefa.com.hisab.data.meal.MealType
import mish.mish.assefa.com.hisab.framework.base.BaseActivity

class MainActivity : BaseActivity(),CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


        val type=MealType.values()[position]
        controller.addMealType(type)
    }

    //Controller
    private val controller=Controller()
    //init
   private lateinit var fetira:CheckBox
    private lateinit var  chechebsa:CheckBox
    private lateinit var fullMedames:CheckBox
    private lateinit var  tibs :CheckBox

    private lateinit var progress:SeekBar
    private lateinit var spinner:Spinner
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {

        if (buttonView==null) return
        val id=buttonView?.id
        val type=when(id){
            R.id.FetiraCheckbox->FoodTypes.Fetira
            R.id.fullMedamesCheckBox->FoodTypes.FullMedames
            R.id.ChechebsaCheckbox->FoodTypes.Chechebsa
            //R.id.TibsCheckBox->FoodTypes.TibsFirfir
            else->FoodTypes.TibsFirfir
        }

        if (isChecked) {
            controller.addFoods(type)
        } else {
            controller.removeFoods(type)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.actvity_mainn)

        fetira=findViewById(R.id.FetiraCheckbox)
         chechebsa=findViewById(R.id.ChechebsaCheckbox)
        fullMedames=findViewById(R.id.fullMedamesCheckBox)
        tibs=findViewById(R.id.TibsCheckBox)


        fetira.setOnCheckedChangeListener(this)
        fullMedames.setOnCheckedChangeListener(this)
        tibs.setOnCheckedChangeListener(this)
        chechebsa.setOnCheckedChangeListener(this)

        /*if (this::fetira.isInitialized){

        }*/

        calculate_btn.setOnClickListener {
            //controller.calculate()
            //Toast.makeText(this,"${controller.calculate()}",Toast.LENGTH_LONG)
        }
//Meal Type Spinner
        meal_type_spinner.onItemSelectedListener=this

         spinner= findViewById(R.id.meal_type_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.meal_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

        }


      progress=findViewById(R.id.pBar)
        progress.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                 total_people_tv.text="Total People: $progress"
                controller.addPeopleDiscount(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                val a=seekBar?.progress
                total_people_tv.text="Total People: $a"
                //controller.addPeopleDiscount(a)

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                val a=seekBar?.progress.toString()
                total_people_tv.text="Total People: $a"
            }

        })
        val a=progress.progress
        d("$a")


    }


}
