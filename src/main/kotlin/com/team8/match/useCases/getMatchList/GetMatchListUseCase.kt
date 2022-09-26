package com.team8.match.useCases.getMatchList

import com.team8.match.repository.IMatchRepository
import com.team8.match.domain.MatchTurn
import com.team8.match.domain.DTO.OnGoingMatchDTO
import com.team8.match.domain.RoundStatus

class GetMatchListUseCase(val matchRespository: IMatchRepository) : IGetMatchListUseCase {

    override suspend operator fun invoke(userId : String) : MutableList<OnGoingMatchDTO>{

        val matchList = matchRespository.getMatchListByUserId(userId)

        val listOnGoingMatchDTO = mutableListOf<OnGoingMatchDTO>()

        matchList.forEach{
            listOnGoingMatchDTO.add(
                OnGoingMatchDTO(
                    it.id,
                    it.challenger,
                    it.opponent,
                    it.currentRound,
                    (it.matchTurn == MatchTurn.Challenger),
                    it.currentRound == it.rounds.size - 1 && it.rounds[it.currentRound].roundStatus == RoundStatus.Finished,
                    arrayOf(it.rounds[0].winner, it.rounds[1].winner, it.rounds[2].winner)
                )
            )
        }
        return listOnGoingMatchDTO
    }
}