package pet.nekos.discord.entities

import pet.nekos.api.entities.*

import net.dv8tion.jda.api.entities.Message as JDAMessage

import pet.nekos.discord.Discord

import java.io.File

class DiscordMessage (
    content: String,
    user: DiscordUser,
    channel: DiscordChannel,
    service: Discord,
    var _jdamessage: JDAMessage,
) : Message(content, user, channel, service) {

    constructor(message: JDAMessage) : this(
        message.contentRaw, 
        DiscordUser(message.author, message.member),
        DiscordChannel(message.channel),
        Discord(),
        message) { }

    override fun reply(content: String, vararg attachments: File ): Boolean {
        var reply = _jdamessage.reply(content)
        for (f in attachments) {
            reply.addFile(f)
        }
        reply.queue()

        return true
    }
}