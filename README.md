## Civitai Generator Java Client

### Installation

```gradle
implementation("io.github.kgmyshin:civitai-generator-client:{latest-version}")
```

### Usage

#### Create a txt2img job

```kotlin
val apiClient = ApiClientFactory.create()
val request = CreateImageRequestJson(
  quantity = 1,
  model = "urn:air:sd1:checkpoint:civitai:4384@128713",
  baseModel = BaseDiffusionModel.SDXL,
  additionalNetworks = null,
  params = ImageJobParams(
    prompt = "RAW photo, face portrait photo of woman, wearing black dress, happy face, hard shadows, cinematic shot, dramatic lighting",
    negativePrompt = "(deformed, distorted, disfigured:1.3)",
    scheduler = Scheduler.EulerA,
    steps = 20,
    cfgScale = 7.0,
    width = 512,
    height = 512,
    seed = null,
    clipSkip = 2
  ),
)
runBlocking {
  val createImageResponse = apiClient.createImage(
    System.getenv("CIVITAI_TOKEN"),
    wait = false,
    body = request
  )
  println(createImageResponse)
}
```

#### Get Jobs by Token

```kotlin
runBlocking {
  val getJobsByTokenResponse = apiClient.getJobsByToken(
    System.getenv("CIVITAI_TOKEN"),
    token = "job token"
  )
  println(getJobsByTokenResponse)
}
```

#### Get Jobs by JobId

```kotlin
runBlocking {
  val getJobsResponseJson = apiClient.getJobsByJobId(
    System.getenv("CIVITAI_TOKEN"),
    jobId = "job id"
  )
  println(getJobsResponseJson)
}
```
