package mish.mish.assefa.com.hisab

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_summary.*
import mish.mish.assefa.com.hisab.framework.base.BaseActivity

class Summary:BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)

        val extras = intent.extras
        if (extras != null) {
            val total = extras.getDouble("total").toString()
            val waiter=extras.getString("Waiter")
           val foods=extras.getString("foods")
            if (foods!!.isEmpty()){
                summarySelectedFoodsLabel.text="Nothing is Selected"
            }else{
                summarySelectedFoodsLabel.text=foods
            }
            val totalDiscount=extras.getDouble("totalDiscount").toFloat().toString()
            val mealType=extras.getString("mealType")!!.toString()

            //summarySelectedFoodsLabel.text=foods
            summaryMealTypeLabel.text=mealType
            summaryTotalDiscountLabel.text="$totalDiscount Birr"
            summaryTotalCostLabel.text="$total Birr"
            summaryServedByLabel.text=waiter
            }
        back_to_main.setOnClickListener {
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
}
