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
import mish.mish.assefa.com.hisab.framework.util.logD

class MainActivity : BaseActivity(),CompoundButton.OnCheckedChangeListener, AdapterView.OnItemSelectedListener {
    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
  var totalPeople=0
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
            if(waiter_name_etv.text.isNotEmpty()){
            var total =calculator()

                var a=""
                for (food in controller.foods){
                    a+="${food.type}          ${food.price}\n"
                }
            Toast.makeText(this,"""Ordered Foods    price
                |$a
                |
                |Meal Type :  ${controller.meal.meal}
                |Total People:  $totalPeople
                |
                |Total Discount: ${totalDiscount()} birr
                |Waiter's Name:   ${waiter_name_etv.text}
                |Calculated Price is:   $total birr


            """.trimMargin(),Toast.LENGTH_LONG).show()
            //Toast.makeText(this,"$total",Toast.LENGTH_LONG)
            logD("Main Activty",
                "$total")}
            else
            {
                Toast.makeText(this,"Enter Waiter's Name ",Toast.LENGTH_SHORT).show()
            }
        }

        clear_all_btn.setOnClickListener {
            clearAll()
            Toast.makeText(this,"History Cleared ,Nothing To show",Toast.LENGTH_LONG).show()

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
                totalPeople=progress
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


    fun calculator():Double{
             var totalPrice=0.0
        for (food in controller.foods){
            totalPrice+=food.price
        }

        totalPrice*=if(totalPeople>4){
            (1-controller.meal.discount)*(1-controller.peopleDiscount)
        }else{
            1-controller.meal.discount
        }

        return totalPrice
    }

    fun totalDiscount():Double{
        var cal =calculator()
        var totalDiscount=1040.0-cal
        return totalDiscount
    }

    fun clearAll(){
        for(food in controller.foods) {
            controller.removeFoods(food.type)
        }
        controller.addMealType(MealType.Breakfast)

        fetira.isChecked=false
        chechebsa.isChecked=false
        tibs.isChecked=false
       fullMedamesCheckBox.isChecked=false
        controller.peopleDiscount=0.0
        progress.progress=1
        waiter_name_etv.setText("")
        total_people_tv.text="Total People : "
        logD("Ac","Cleared")

    }

}
