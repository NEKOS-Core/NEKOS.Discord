package pet.nekos.discord.entities

import pet.nekos.api.user.User

class DiscordUser (
    name: String,
    nickname: String = name,
    hash: String
) : User(name, nickname, hash){ }