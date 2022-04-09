package pet.nekos.discord.entities

import pet.nekos.api.message.ChatMessage

import net.dv8tion.jda.api.entities.Message as JDAMessage

import pet.nekos.discord.Discord

class DiscordMessage (
    content: String,
    user: DiscordUser,
    channel: DiscordChannel,
    var _jdamessage: JDAMessage,
    var _service: Discord
) : ChatMessage(content, user, channel, _service) {
    override fun reply(content: String): Boolean {
        _jdamessage.reply(content).queue()
        return true
    }
}