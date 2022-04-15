package com.notiprice.scarper

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class ScarperTest {

    @Test
    fun getHtml() {
        //   //*[@id="vue-root"]/x-app-content/div/div[2]/div[2]/div[2]/span
        Scarper().getData(
            "https://www.lamoda.ru/p/rtlaas316501/clothes-ea7-kostyum-sportivnyy/",
            "//*[@id=\"vue-root\"]/x-app-content/div/div[2]/div[2]/div[2]/span"
        )

    }
}