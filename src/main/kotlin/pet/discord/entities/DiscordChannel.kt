package pet.nekos.discord.entities

import pet.nekos.api.channel.Channel
import pet.nekos.api.user.User

import net.dv8tion.jda.api.entities.Channel as JDAChannel
import net.dv8tion.jda.api.entities.User as JDAUser
import net.dv8tion.jda.api.entities.PrivateChannel
import net.dv8tion.jda.api.entities.IMemberContainer

import pet.nekos.discord.Discord

class DiscordChannel(
    name: String,
    service: Discord,
    var _jdachannel: JDAChannel
) : Channel(name, service) {

    constructor(channel: JDAChannel) : this(
        channel.getName(),
        Discord(),
        channel) { }

    override fun getUsers(): Array<User> {
        var users = mutableListOf<User>()
        if (_jdachannel is PrivateChannel) {
            users.add((DiscordUser((_jdachannel as PrivateChannel).getUser() as JDAUser)))
            users.add(DiscordUser(_jdachannel.getJDA().getSelfUser()))
        } else if (_jdachannel is IMemberContainer) {
            for (u in (_jdachannel as IMemberContainer).getMembers()) {
                users.add(DiscordUser(u as JDAUser))
            }
        }
        return users.toTypedArray()
    }
}