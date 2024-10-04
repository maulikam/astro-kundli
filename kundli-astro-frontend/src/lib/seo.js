// src/lib/seo.js
import Head from 'next/head';

/**
 * Set SEO Meta Tags for a page
 * @param {Object} options - SEO options for the page
 * @param {string} options.title - The title of the page
 * @param {string} options.description - The description of the page
 * @param {string} options.keywords - The keywords for the page
 * @param {string} [options.ogType] - The Open Graph type (e.g., 'website', 'article')
 * @param {string} [options.ogImage] - The URL of the Open Graph image
 * @param {string} [options.ogUrl] - The URL of the page (useful for Open Graph)
 * @param {string} [options.twitterCard] - The Twitter card type (e.g., 'summary', 'summary_large_image')
 */
export const setSeoMetaTags = ({
  title,
  description,
  keywords,
  ogType = 'website',
  ogImage = '/default-og-image.jpg', // Default image to be used for sharing
  ogUrl = '',
  twitterCard = 'summary_large_image',
}) => (
  <Head>
    {/* Basic SEO */}
    <title>{title} | Kundli Astro</title>
    <meta name="description" content={description} />
    <meta name="keywords" content={keywords} />

    {/* Open Graph Meta Tags for Social Sharing */}
    <meta property="og:type" content={ogType} />
    <meta property="og:title" content={title} />
    <meta property="og:description" content={description} />
    <meta property="og:image" content={ogImage} />
    {ogUrl && <meta property="og:url" content={ogUrl} />}

    {/* Twitter Meta Tags */}
    <meta name="twitter:card" content={twitterCard} />
    <meta name="twitter:title" content={title} />
    <meta name="twitter:description" content={description} />
    <meta name="twitter:image" content={ogImage} />
  </Head>
);

export default {
  setSeoMetaTags,
};
