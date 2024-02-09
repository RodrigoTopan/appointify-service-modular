package adapters.in.http.handlers.evaluation.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import adapters.in.http.handlers.evaluation.mapper.EvaluationMapper;
import adapters.in.http.handlers.evaluation.contract.query.FindEvaluationQueryResponse;
import ports.output.repository.EvaluationRepository;
import adapters.in.http.handlers.evaluation.EvaluationQueryHandler;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EvaluationQueryHandlerImpl implements EvaluationQueryHandler {
    private final EvaluationMapper evaluationMapper;
    private final EvaluationRepository evaluationRepository;

    @Override
    public List<FindEvaluationQueryResponse> findAll() {
        return evaluationRepository.findAll()
                .stream()
                .map(evaluationMapper::evaluationToFindEvaluationQueryResponse)
                .collect(Collectors.toList());
    }
}