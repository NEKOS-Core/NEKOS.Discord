package pet.nekos.discord.entities

import pet.nekos.api.entities.channel.GuildChannel
import pet.nekos.api.entities.user.User

import net.dv8tion.jda.api.entities.GuildChannel as JDAGUildChannel
import net.dv8tion.jda.api.entities.User as JDAUser
import net.dv8tion.jda.api.entities.IMemberContainer

import pet.nekos.discord.Discord

class DiscordGuildChannel(
    name: String,
    guild: DiscordGuild,
    service: Discord,
    var _jdachannel: JDAGUildChannel
) : GuildChannel(name, guild, service) {

    constructor(channel: JDAGUildChannel) : this(
        channel.getName(),
        DiscordGuild(channel.getGuild()),
        Discord(),
        channel) { }

        override fun getUsers(): Array<User> {
            var users = mutableListOf<User>()
            if (_jdachannel is IMemberContainer) {
                for (u in (_jdachannel as IMemberContainer).getMembers()) {
                    users.add(DiscordUser(u as JDAUser))
                }
            }
            return users.toTypedArray()
        }
    }