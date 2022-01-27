package com.example.halanchallenge.utils.base
import com.example.halanchallenge.ui.entities.Intent
import com.example.halanchallenge.ui.entities.State
import kotlinx.coroutines.flow.Flow


interface MviView < IntentType : Intent , StateType : State  > {

    public fun render ( state : StateType )

    public fun collectOurIntents () : Flow<IntentType>

}