package com.example.halanchallenge.utils.base
import kotlinx.coroutines.flow.Flow


interface MviView < IntentType : Intent , StateType : State  > {

    public fun render ( state : StateType )

    public fun collectOurIntents () : Flow<IntentType>

}