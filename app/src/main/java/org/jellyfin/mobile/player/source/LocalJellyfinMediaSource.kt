package org.jellyfin.mobile.player.source

import android.net.Uri
import androidx.media3.common.MediaItem
import org.jellyfin.sdk.model.api.BaseItemDto
import org.jellyfin.sdk.model.api.MediaSourceInfo
import org.jellyfin.sdk.model.api.MediaStream
import org.jellyfin.sdk.model.api.PlayMethod
import java.util.UUID

class LocalJellyfinMediaSource(
    itemId: UUID,
    item: BaseItemDto?,
    sourceInfo: MediaSourceInfo,
    playSessionId: String,
    playbackDetails: PlaybackDetails? = null,
    val remoteFileUri: Uri,
    val localSubtitleConfigurations: List<MediaItem.SubtitleConfiguration> = emptyList(),
    additionalMediaStreams: List<MediaStream> = emptyList(),
) : JellyfinMediaSource(itemId, item, sourceInfo, playSessionId, playbackDetails, additionalMediaStreams) {
    override val playMethod: PlayMethod = PlayMethod.DIRECT_PLAY
}
