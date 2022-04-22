package pet.nekos.discord.entities

import net.dv8tion.jda.api.entities.Member as JDAMember

import pet.nekos.api.user.Member

class DiscordMember (
    user: DiscordUser,
    guild: DiscordGuild,
    _jdamember: JDAMember
) : Member(user, guild){ 
        
    constructor(member: JDAMember) : this(
        DiscordUser(member.getUser()),
        DiscordGuild(member.getGuild()),
        member) { }

}