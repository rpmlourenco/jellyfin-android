package org.jellyfin.mobile.downloads

import org.jellyfin.sdk.model.api.MediaStream
import org.jellyfin.sdk.model.api.MediaStreamType

internal fun getDownloadSubtitleFileNames(
    mainFileName: String,
    mediaStreams: List<MediaStream>,
): Map<Int, String> {
    val baseName = mainFileName.substringBeforeLast('.', mainFileName)
    val subtitleStreams = mediaStreams.filter { stream ->
        stream.type == MediaStreamType.SUBTITLE &&
            stream.isExternal &&
            stream.codec?.lowercase() in setOf("srt", "subrip")
    }
    val usedNames = mutableSetOf<String>()

    return buildMap {
        for (stream in subtitleStreams) {
            val originalName = stream.path
                ?.substringAfterLast('/')
                ?.substringAfterLast('\\')
                ?.takeIf { it.endsWith(".srt", ignoreCase = true) }

            val language = stream.language
                ?.trim()
                ?.takeUnless { it.isBlank() || it.equals("und", ignoreCase = true) }
                ?.replace(Regex("""[^A-Za-z0-9_-]"""), "_")

            var fileName = originalName ?: when {
                subtitleStreams.size == 1 -> "$baseName.srt"
                language != null -> "$baseName.$language.srt"
                else -> "$baseName.sub${stream.index}.srt"
            }

            if (!usedNames.add(fileName.lowercase())) {
                fileName = "$baseName.${language ?: "sub"}.${stream.index}.srt"
                usedNames += fileName.lowercase()
            }

            put(stream.index, fileName)
        }
    }
}
