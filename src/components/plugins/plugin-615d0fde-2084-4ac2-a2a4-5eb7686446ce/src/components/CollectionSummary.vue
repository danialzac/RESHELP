<template>
    <div class="rss-feed-collection-summary">
        <div class="rss-feed-collection-summary__elem caption-s">URL: {{ config.url || '-' }}</div>
    </div>
</template>

<script>
export default {
    props: {
        config: { type: Object, required: true },
    },
};
</script>

<style lang="scss" scoped>
.rss-feed-collection-summary {
    &__elem {
        margin-bottom: var(--ww-spacing-01);
    }
}
</style>
