package pet.nekos.discord

import pet.nekos.api.service.Service

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

class Discord : Service() {
    var jda: JDA? = null

    override fun initService(): Boolean {
        jda = JDABuilder.createLight(System.getenv("BOT_TOKEN"))
        .addEventListeners(Listener()).build()
        return true
    }

    override fun sendMessage(content: String): Boolean {
        try {
            jda?.getTextChannelById(System.getenv("BOT_CHANNEL"))?.sendMessage(content)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }

}