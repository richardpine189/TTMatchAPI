package com.team8.match.useCases.getMatchList

import com.team8.match.repository.IMatchRepository
import com.team8.match.domain.MatchTurn
import com.team8.match.domain.DTO.OnGoingMatchDTO
import com.team8.match.domain.Parsers.MatchParser
import com.team8.match.domain.RoundStatus

class GetMatchListUseCase(val matchRespository: IMatchRepository) : IGetMatchListUseCase {

    override suspend operator fun invoke(userId : String) : MutableList<OnGoingMatchDTO>{

        val matchList = matchRespository.getMatchListByUserId(userId)

        val listOnGoingMatchDTO = mutableListOf<OnGoingMatchDTO>()

        matchList.forEach{
            listOnGoingMatchDTO.add(
                MatchParser.toOngoingMatchDto(it)
            )
        }

        return listOnGoingMatchDTO
    }
}