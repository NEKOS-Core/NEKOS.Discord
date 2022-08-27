package pet.nekos.discord.entities

import pet.nekos.api.entities.*

import net.dv8tion.jda.api.entities.User as JDAUser
import net.dv8tion.jda.api.entities.Guild as JDAGuild
import net.dv8tion.jda.api.entities.GuildChannel as JDAGuildChannel

import pet.nekos.discord.entities.*
import pet.nekos.discord.Discord

class DiscordGuild(
    name: String,
    service: Discord,
    var _jdaguild: JDAGuild
) : Guild(name, service) {

    constructor(guild: JDAGuild) : this(
        guild.getName(),
        Discord(),
        guild) { }

    override fun getChannels(): Array<Channel> {
        var channels = mutableListOf<Channel>()
        for (gc in _jdaguild.getChannels(true)) {
            channels.add(DiscordChannel(gc))
        }
        return channels.toTypedArray()
    }

    override fun getMembers(): Array<User> {
        var users = mutableListOf<User>()
        for (u in (_jdaguild).getMembers()) {
            users.add(DiscordUser(u as JDAUser))
        }
        return users.toTypedArray()
    }

}