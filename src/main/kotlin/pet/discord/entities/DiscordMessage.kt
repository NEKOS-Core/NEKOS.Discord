package pet.nekos.discord.entities

import pet.nekos.api.message.ChatMessage

import net.dv8tion.jda.api.entities.Message as JDAMessage

import pet.nekos.discord.Discord

class DiscordMessage (
    content: String,
    user: DiscordUser,
    channel: DiscordChannel,
    var _jdamessage: JDAMessage
) : ChatMessage(content, user, channel) {
    override fun reply(content: String): Boolean {
        Discord().sendMessage(content, channel as DiscordChannel)
        println(_jdamessage.reply(content))
        println(_jdamessage)
        return true
    }
}