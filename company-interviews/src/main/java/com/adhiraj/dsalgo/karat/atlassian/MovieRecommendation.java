package com.adhiraj.dsalgo.karat.atlassian;

/**
 * data -> [{{USER}}, {{MOVIE}}, {{RATING}}] :: Means, {{USER}} has seen {{MOVIE}} and rated it with {{RATING}}
 * {{Rating can be 1, 2, 3, 4 or 5}}
 *
 * Given a series of this data, recommend a (not seen) movie for any user based on the other users, with same taste,
 * recommendations.
 *
 * What is 2 users with similar taste:
 * 1. Both have seen a common movie
 * 2. Both have rated the common movie a 4 or 5
 *
 * What to recommend:
 * 1. Movie seen by other users with common taste
 * 2. Movie not seen by current user
 * 3. Movie rated 4 or 5
 */
public class MovieRecommendation {
}
