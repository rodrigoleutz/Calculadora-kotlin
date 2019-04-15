package br.com.uware.calculadora

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //  Botões de Números
        bu1.setOnClickListener(){
            buClick(bu1)
        }
        bu2.setOnClickListener(){
            buClick(bu2)
        }
        bu3.setOnClickListener(){
            buClick(bu3)
        }
        bu4.setOnClickListener(){
            buClick(bu4)
        }
        bu5.setOnClickListener(){
            buClick(bu5)
        }
        bu6.setOnClickListener(){
            buClick(bu6)
        }
        bu7.setOnClickListener(){
            buClick(bu7)
        }
        bu8.setOnClickListener(){
            buClick(bu8)
        }
        bu9.setOnClickListener(){
            buClick(bu9)
        }
        bu0.setOnClickListener(){
            buClick(bu0)
        }
        buDot.setOnClickListener(){
            buClick(buDot)
        }
        buPlusMiss.setOnClickListener(){
            buClick(buPlusMiss)
        }

        // Botões de Operações
        buSum.setOnClickListener(){
            btnClickOp(buSum)
        }
        buSub.setOnClickListener(){
            btnClickOp(buSub)
        }
        buMul.setOnClickListener(){
            btnClickOp(buMul)
        }
        buDiv.setOnClickListener(){
            btnClickOp(buDiv)
        }
        buPer.setOnClickListener(){
            btnClickOp(buPer)
        }
        buEq.setOnClickListener(){
            btnClickOp(buEq)
        }

        // Botões Especiais
        buAc.setOnClickListener(){
            resetOper()
        }
        buDel.setOnClickListener(){
            deletar()
        }

    }
    var conta: String = ""
    var oldNum: String = ""
    var contaInteira: String = ""
    var oper: String = "*"
    var oldOper: String = "*"
    var digitNum: Boolean = false
    var newOp: Boolean = true
    var conOp: Boolean = false
    var pointBreak = false
    var minus = false
    var result: Double? = null

    fun resetOper(){
        conta = "0"
        contaInteira = ""
        oper = "*"
        newOp = true
        conOp = false
        pointBreak = false
        minus = false
        tvConta.text = contaInteira
        tvResult.text = conta
    }
    fun deletar(){
        if(conta.length>0) {
            if(conta.substring(conta.length - 1, conta.length) == ".") pointBreak = false
            if(conta.substring(conta.length - 1, conta.length) == "-") minus = false
            conta = conta.substring(0, conta.length - 1)
        }
        tvResult.text = conta
    }
    fun buClick(btn: Button){
        if(newOp){
            conta = ""
        }
        when(btn){
            bu0 -> {
                if(conta == "0"){
                    conta = conta
                }
                else {
                    conta = conta + "0"
                }
            }
            bu1 -> conta = conta+"1"
            bu2 -> conta = conta+"2"
            bu3 -> conta = conta+"3"
            bu4 -> conta = conta+"4"
            bu5 -> conta = conta+"5"
            bu6 -> conta = conta+"6"
            bu7 -> conta = conta+"7"
            bu8 -> conta = conta+"8"
            bu9 -> conta = conta+"9"
            buDot -> {
                if(!pointBreak) {
                    conta = conta + "."
                    pointBreak = true
                }
            }
            buPlusMiss -> {
                if(!minus) {
                    conta = "-" + conta
                    minus = true
                }
                else{
                    conta = conta.substring(1,conta.length)
                    minus = false
                }
            }
        }
        tvResult.text = conta
        newOp = false
        digitNum = true
    }
    fun btnClickOp(btn: Button){
        if(digitNum) {
            if (!conOp) {
                when (btn) {
                    buSum -> oper = "+"
                    buSub -> oper = "-"
                    buMul -> oper = "*"
                    buDiv -> oper = "/"
                    buPer -> oper = "%"
                    buEq -> conta = "Err"
                }
                oldNum = conta
                oldOper = oper
            } else {
                when (oldOper) {
                    "+" -> result = somar(oldNum, conta)
                    "-" -> result = diminuir(oldNum, conta)
                    "*" -> result = multiplicar(oldNum, conta)
                    "/" -> result = dividir(oldNum, conta)
                    "%" -> result = porcento(oldNum, conta)
                    "=" -> oldNum
                }
                oldNum = result.toString()
                when (btn) {
                    buSum -> oper = "+"
                    buSub -> oper = "-"
                    buMul -> oper = "*"
                    buDiv -> oper = "/"
                    buPer -> oper = "%"
                    buEq -> {
                        if (oldNum.substring(oldNum.length - 2, oldNum.length) == ".0") oldNum =
                            oldNum.substring(0, oldNum.length - 2)
                        oper = "=" + oldNum
                    }
                }
                oldOper = oper
            }
            contaInteira = contaInteira + conta + oper
            tvConta.text = contaInteira
            tvResult.text = oldNum
            if (btn == buEq) {
                contaInteira = oldNum
                conta = ""
            }
            minus = false
            pointBreak = false
            newOp = true
            conOp = true
        }
        else{
            tvResult.text = "Err"
        }
    }
    fun somar(val1: String, val2: String): Double{
        var resp: Double
        resp = val1.toDouble()?.plus(val2.toDouble())
        conOp = true
        return resp
    }
    fun diminuir(val1: String, val2: String): Double{
        var resp: Double
        resp = val1.toDouble()?.minus(val2.toDouble())
        conOp = true
        return resp
    }
    fun multiplicar(val1: String, val2: String): Double{
        var resp: Double
        resp = val1.toDouble() * (val2.toDouble())
        conOp = true
        return resp
    }
    fun dividir(val1: String, val2: String): Double{
        var resp: Double
        resp = val1.toDouble() / (val2.toDouble())
        conOp = true
        return resp
    }
    fun porcento(val1: String, val2: String): Double{
        var resp: Double
        resp = (val2.toDouble()/100)*val1.toDouble()
        conOp = true
        return resp
    }


}
