package pet.nekos.discord

import pet.nekos.api.service.ChatService
import pet.nekos.api.channel.Channel
import pet.nekos.discord.entities.DiscordChannel

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder

class Discord : ChatService() {
    companion object {
        var jda: JDA? = null
    }

    override fun initService(): Boolean {
        jda = JDABuilder.createLight(System.getenv("BOT_TOKEN"))
        .addEventListeners(Listener()).build()
        jda?.getTextChannelById(929180392047669258)?.sendMessage("Testing message")
        return true
    }

    override fun sendMessage(content: String, channel: Channel): Boolean {
        try {
            channel as DiscordChannel
            jda?.getTextChannelById(channel._jdachannel.id)?.sendMessage(content)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }


}