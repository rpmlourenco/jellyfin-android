package org.jellyfin.mobile.player

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TrackSelectionHelperTest {
    @Test
    fun `track ids are sorted by their numeric components`() {
        val ids = listOf("1/10", "1/2", "1/1", "1/11")

        assertEquals(
            listOf("1/1", "1/2", "1/10", "1/11"),
            ids.sortedBy(::naturalTrackIdSortKey),
        )
    }

    @Test
    fun `plain numeric track ids retain numeric ordering`() {
        val ids = listOf("10", "2", "1", "11")

        assertEquals(
            listOf("1", "2", "10", "11"),
            ids.sortedBy(::naturalTrackIdSortKey),
        )
    }
}
