package com.notiprice.scarper

import com.notiprice.scanner.getValueByXpath
import org.junit.jupiter.api.Test


internal class ScarperTest {

    @Test
    fun getHtml() {
        //   //*[@id="vue-root"]/x-app-content/div/div[2]/div[2]/div[2]/span

        val s = getValueByXpath(
            "https://www.lamoda.ru/p/rtlaas316501/clothes-ea7-kostyum-sportivnyy/",
            "//*[@id=\"vue-root\"]/div/div/div[2]/div[2]/div[2]/div[1]/div/span/span"
        )

        val d = getValueByXpath(
            "https://www.lamoda.ru/p/rtlaas316501/clothes-ea7-kostyum-sportivnyy/",
            "//*[@id=\"vue-root\"]/x-app-content/div/div[2]/div[2]/div[2]/span"
        )




    }
}