package com.calex.aplicacion.view

import tornadofx.*
import javafx.scene.control.Alert
import javafx.scene.control.TextField
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane

import javafx.scene.layout.VBox
import java.time.*;


class MainView : View("Actividades") {

    fun diaSemana(ano: Int, mes: Int, dia: Int): DayOfWeek {
        val date = LocalDate.of(ano, mes, dia)
        val dia5 = date.getDayOfWeek()
        return dia5
    }



    var ano2 = TextField("")
    var mes2 = TextField("")
    var dia2 = TextField("")

    var diaBuscado: String = "MONDAY"

    var resDia: Boolean = false

    fun calculoCuota(capital: Double, interes: Double, anos: Double): Double {
        var inter: Double = interes / 12
        val plazo: Double = anos * 12
        val a: Double = 1 + inter / 100
        val b: Double = Math.pow(a, -plazo)
        var cuota: Double = capital * inter / (100 * (1 - b))
        cuota = Math.round(cuota).toDouble()

        return cuota
    }

    var capital2 = TextField("")
    var interes2 = TextField("")
    var anos2 = TextField("")

    var cuota2: Double = 0.0

    var resCuota: Boolean = false




    override val root = stackpane {

        borderpane {
            top {
                menubar {
                    menu("Actividades") {

                        item("Hipoteca", "Shortcut+H").action {


                            center {

                                form {
                                    fieldset {

                                        label("Cuota hipoteca")

                                        field("Capital") {
                                            capital2 = textfield()
                                        }
                                        field("Interés") {
                                            interes2 = textfield()
                                        }
                                        field("Años") {
                                            anos2 = textfield()
                                        }

                                        button("Calcular cuota").setOnAction {

                                            //si hay campos vacios o strings en vez de números salta alert indicando error
                                            resCuota =
                                                (capital2.text.toString() == "") || (interes2.text.toString() == "") || (anos2.text.toString() == "") || !(capital2.text.toString().matches(
                                                    "-?\\d+(\\.\\d+)?".toRegex()
                                                )) || !(interes2.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex())) || !(anos2.text.toString().matches(
                                                    "-?\\d+(\\.\\d+)?".toRegex()
                                                ))

                                            if (resCuota == true) {
                                                alert(Alert.AlertType.INFORMATION, "Error, campos obligatorios")
                                            } else {
                                                cuota2 =
                                                    calculoCuota(
                                                        capital2.text.toDouble(),
                                                        interes2.text.toDouble(),
                                                        anos2.text.toDouble()
                                                    )

                                                alert(Alert.AlertType.INFORMATION, cuota2.toString())
                                            }
                                        }
                                    }

                                }

                            }
                            }

                            item("Dia de la semana", "Shortcut+D").action {

                                center {

                                        form {
                                            fieldset {

                                                label("Dia de la semana")

                                                field("Año") {
                                                    ano2 = textfield()
                                                }
                                                field("Mes") {
                                                    mes2 = textfield()
                                                }
                                                field("Dia") {
                                                    dia2 = textfield()
                                                }

                                                button("Calcular dia").setOnAction {


                                                    //si hay campos vacios o strings en vez de números salta alert indicando error
                                                    resDia =
                                                        (ano2.text.toString() == "") || (mes2.text.toString() == "") || (dia2.text.toString() == "") || !(ano2.text.toString().matches(
                                                            "-?\\d+(\\.\\d+)?".toRegex()
                                                        )) || !(mes2.text.toString().matches("-?\\d+(\\.\\d+)?".toRegex())) || !(dia2.text.toString().matches(
                                                            "-?\\d+(\\.\\d+)?".toRegex()
                                                        )) || (dia2.text.toInt() > 31 || dia2.text.toInt() < 1) || (mes2.text.toInt() > 12 || mes2.text.toInt() < 1)

                                                    if (resDia == true) {
                                                        alert(Alert.AlertType.INFORMATION, "Error, en los campos")
                                                    } else {
                                                        diaBuscado =
                                                            diaSemana(
                                                                ano2.text.toInt(),
                                                                mes2.text.toInt(),
                                                                dia2.text.toInt()
                                                            ).toString()

                                                        alert(Alert.AlertType.INFORMATION, diaBuscado)
                                                        //alert(Alert.AlertType.INFORMATION, diaBuscado.toString())
                                                    }
                                                }
                                            }

                                        }


                                }
                            }
                            item("Salir", "Shortcut+Q").action {
                                close()
                            }


                    }

                }
            }

        }
    }
}