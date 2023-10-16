package batch.infra.incrementer

import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.JobParametersIncrementer

class ParamCleanRunIdIncrementer : JobParametersIncrementer {

  companion object {
    const val DEFAULT_VALUE: Long = 0L
    const val RUND_ID_KEY = "run.id"
  }

  override fun getNext(parameters: JobParameters?): JobParameters {
    val jobParameters = parameters ?: JobParameters()

    val id = (jobParameters.getLong(RUND_ID_KEY) ?: DEFAULT_VALUE) + 1

    return JobParametersBuilder()
      .addLong(RUND_ID_KEY, id)
      .toJobParameters()
  }
}
