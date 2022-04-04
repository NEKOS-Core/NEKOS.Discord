package pet.nekos.discord.entities

import pet.nekos.api.channel.GuildChannel
import pet.nekos.api.user.User

import net.dv8tion.jda.api.entities.GuildChannel as JDAGuildChannel
import net.dv8tion.jda.api.entities.User as JDAUser

class DiscordChannel(
    name: String,
    guild: DiscordGuild,
    var _jdachannel: JDAGuildChannel
) : GuildChannel(name, guild) {
    override fun getUsers(): Array<User> {
        var users = mutableListOf<User>()
        for (u in _jdachannel.getGuild().getMembers()) {
            users.add(DiscordUser(u.getUser().getName(), u.getEffectiveName(), "TEMPORARY"))
        }
        return users.toTypedArray()
    }
}