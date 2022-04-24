package pet.nekos.discord

import pet.nekos.api.service.ChatService
import pet.nekos.api.entities.channel.Channel
import pet.nekos.api.entities.user.User

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.User as JDAUser

import pet.nekos.discord.entities.DiscordChannel
import pet.nekos.discord.entities.DiscordUser

import java.io.File

class Discord : ChatService() {
    override var name = "Discord"

    companion object {
        @JvmStatic
        var jda: JDA? = null
        var selfUser: User? = null
    }

    /**
     * Gets the user this service is running from
     * @return User that this service is running as
     */
    override fun getSelfUser(): User? {
        return selfUser
    }

    override fun initService(): Boolean {
        jda = JDABuilder.createLight(System.getenv("BOT_TOKEN"))
        .addEventListeners(Listener()).build()
        
        selfUser = DiscordUser(jda?.getSelfUser() as JDAUser)

        return true
    }

    override fun sendMessage(content: String, channel: Channel, vararg attachments: File): Boolean {
        try {
            channel as DiscordChannel
            var message = jda?.getTextChannelById(channel._jdachannel.id)?.sendMessage(content)
            for (f in attachments) {
                message?.addFile(f)
            }
            message?.queue()
    
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }

    override fun sendMessage(content: String, vararg attachments: File): Boolean {
        try {
            var message = jda?.getTextChannelById(System.getenv("DEFAULT_CHANNEL"))?.sendMessage(content)
            for (f in attachments) {
                message?.addFile(f)
            }
            message?.queue()

        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }


}