package dkgroup.kz.onlinedoctor

import ru.terrakok.cicerone.commands.Command

class StartFlow(
        val screenKey: String,
        val transitionData: Any?
) : Command