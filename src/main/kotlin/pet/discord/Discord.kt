package pet.nekos.discord

import pet.nekos.api.service.ChatService
import pet.nekos.api.channel.Channel
import pet.nekos.api.user.User

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.User as JDAUser

import pet.nekos.discord.entities.DiscordChannel
import pet.nekos.discord.entities.DiscordUser

class Discord : ChatService() {
    override var name = "Discord"

    companion object {
        var jda: JDA? = null
        var selfUser: User? = null
    }

    override fun initService(): Boolean {
        jda = JDABuilder.createLight(System.getenv("BOT_TOKEN"))
        .addEventListeners(Listener()).build()

        selfUser = DiscordUser(jda?.getSelfUser() as JDAUser)
        return true
    }

    override fun sendMessage(content: String, channel: Channel): Boolean {
        try {
            channel as DiscordChannel
            jda?.getTextChannelById(channel._jdachannel.id)?.sendMessage(content)?.queue()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }

    override fun sendMessage(content: String): Boolean {
        try {
            jda?.getTextChannelById(System.getenv("DEFAULT_CHANNEL"))?.sendMessage(content)?.queue()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }


}