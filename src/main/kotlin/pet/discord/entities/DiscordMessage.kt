package pet.nekos.discord.entities

import pet.nekos.api.message.ChatMessage

import net.dv8tion.jda.api.entities.Message as JDAMessage

import pet.nekos.discord.Discord

class DiscordMessage (
    content: String,
    user: DiscordUser,
    channel: DiscordChannel,
    service: Discord,
    var _jdamessage: JDAMessage,
) : ChatMessage(content, user, channel, service) {

    constructor(message: JDAMessage) : this(
        message.contentRaw, 
        DiscordUser(message.author, message.member),
        DiscordChannel(message.channel),
        Discord(),
        message) { }

    override fun reply(content: String): Boolean {
        _jdamessage.reply(content).queue()
        return true
    }
}