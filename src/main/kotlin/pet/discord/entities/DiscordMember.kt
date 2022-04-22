package pet.nekos.discord.entities

import net.dv8tion.jda.api.entities.Member as JDAMember

import pet.nekos.api.user.Member

import pet.nekos.discord.Discord

class DiscordMember (
    user: DiscordUser,
    guild: DiscordGuild,
    service: Discord,
    _jdamember: JDAMember
) : Member(user, guild, service){ 
        
    constructor(member: JDAMember) : this(
        DiscordUser(member.getUser()),
        DiscordGuild(member.getGuild()),
        Discord(),
        member) { }

}