package de.agilecoders.wicket.extensions.markup.html.bootstrap.icon;

import java.util.ArrayList;
import java.util.List;

/**
 * A builder to build {@link FontAwesome5IconType} <br />
 * <br />
 * <b>Examples :</b>
 * <ul>
 * <li>
 * To rotate an icon 90° :
 * <code>	FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).rotate(Rotation.rotate_90).build()</code></li>
 * <li>
 * To spin an icon : <code>	FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).spin().build()</code></li>
 * <li>To resize an icon 4 times :
 * <code>	FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).size(Size.four).build()</code></li>
 * <li>
 * All together :
 * <code>FontAwesomeIconTypeBuilder.on(FontAwesomeGraphic.key).size(Size.four).spin().rotate(Rotation.rotate_90).build()</code>
 * </li>
 * </ul>
 */
public class FontAwesome5IconTypeBuilder {

    interface FontAwesome5Graphic {
        String getPrefix();

        String getIconName();
    }

    /**
     * All icons of style 'solid' available for free in Font Awesome.
     */
    public enum FontAwesome5Solid implements FontAwesome5Graphic {
        ad, address_book, address_card, adjust, air_freshener, align_center, align_justify, align_left, align_right,
        allergies, ambulance, american_sign_language_interpreting, anchor, angle_double_down, angle_double_left,
        angle_double_right, angle_double_up, angle_down, angle_left, angle_right, angle_up, angry, ankh, apple_alt,
        archive, archway, arrow_alt_circle_down, arrow_alt_circle_left, arrow_alt_circle_right, arrow_alt_circle_up,
        arrow_circle_down, arrow_circle_left, arrow_circle_right, arrow_circle_up, arrow_down, arrow_left, arrow_right,
        arrow_up, arrows_alt, arrows_alt_h, arrows_alt_v, assistive_listening_systems, asterisk, at, atlas, atom,
        audio_description, award, baby, baby_carriage, backspace, backward, bacon, balance_scale, ban, band_aid,
        barcode, bars, baseball_ball, basketball_ball, bath, battery_empty, battery_full, battery_half, battery_quarter,
        battery_three_quarters, bed, beer, bell, bell_slash, bezier_curve, bible, bicycle, binoculars, biohazard,
        birthday_cake, blender, blender_phone, blind, blog, bold, bolt, bomb, bone, bong, book, book_dead, book_medical,
        book_open, book_reader, bookmark, bowling_ball, box, box_open, boxes, braille, brain, bread_slice, briefcase,
        briefcase_medical, broadcast_tower, broom, brush, bug, building, bullhorn, bullseye, burn, bus, bus_alt,
        business_time, calculator, calendar, calendar_alt, calendar_check, calendar_day, calendar_minus, calendar_plus,
        calendar_times, calendar_week, camera, camera_retro, campground, candy_cane, cannabis, capsules, car, car_alt,
        car_battery, car_crash, car_side, caret_down, caret_left, caret_right, caret_square_down, caret_square_left,
        caret_square_right, caret_square_up, caret_up, carrot, cart_arrow_down, cart_plus, cash_register, cat,
        certificate, chair, chalkboard, chalkboard_teacher, charging_station, chart_area, chart_bar, chart_line,
        chart_pie, check, check_circle, check_double, check_square, cheese, chess, chess_bishop, chess_board,
        chess_king, chess_knight, chess_pawn, chess_queen, chess_rook, chevron_circle_down, chevron_circle_left,
        chevron_circle_right, chevron_circle_up, chevron_down, chevron_left, chevron_right, chevron_up, child, church,
        circle, circle_notch, city, clinic_medical, clipboard, clipboard_check, clipboard_list, clock, clone,
        closed_captioning, cloud, cloud_download_alt, cloud_meatball, cloud_moon, cloud_moon_rain, cloud_rain,
        cloud_showers_heavy, cloud_sun, cloud_sun_rain, cloud_upload_alt, cocktail, code, code_branch, coffee, cog,
        cogs, coins, columns, comment, comment_alt, comment_dollar, comment_dots, comment_medical, comment_slash,
        comments, comments_dollar, compact_disc, compass, compress, compress_arrows_alt, concierge_bell, cookie,
        cookie_bite, copy, copyright, couch, credit_card, crop, crop_alt, cross, crosshairs, crow, crown, crutch, cube,
        cubes, cut, database, deaf, democrat, desktop, dharmachakra, diagnoses, dice, dice_d20, dice_d6, dice_five,
        dice_four, dice_one, dice_six, dice_three, dice_two, digital_tachograph, directions, divide, dizzy, dna, dog,
        dollar_sign, dolly, dolly_flatbed, donate, door_closed, door_open, dot_circle, dove, download, drafting_compass,
        dragon, draw_polygon, drum, drum_steelpan, drumstick_bite, dumbbell, dumpster, dumpster_fire, dungeon, edit,
        egg, eject, ellipsis_h, ellipsis_v, envelope, envelope_open, envelope_open_text, envelope_square, equals,
        eraser, ethernet, euro_sign, exchange_alt, exclamation, exclamation_circle, exclamation_triangle, expand,
        expand_arrows_alt, external_link_alt, external_link_square_alt, eye, eye_dropper, eye_slash, fast_backward,
        fast_forward, fax, feather, feather_alt, female, fighter_jet, file, file_alt, file_archive, file_audio,
        file_code, file_contract, file_csv, file_download, file_excel, file_export, file_image, file_import,
        file_invoice, file_invoice_dollar, file_medical, file_medical_alt, file_pdf, file_powerpoint, file_prescription,
        file_signature, file_upload, file_video, file_word, fill, fill_drip, film, filter, fingerprint, fire, fire_alt,
        fire_extinguisher, first_aid, fish, fist_raised, flag, flag_checkered, flag_usa, flask, flushed, folder,
        folder_minus, folder_open, folder_plus, font, football_ball, forward, frog, frown, frown_open, funnel_dollar,
        futbol, gamepad, gas_pump, gavel, gem, genderless, ghost, gift, gifts, glass_cheers, glass_martini,
        glass_martini_alt, glass_whiskey, glasses, globe, globe_africa, globe_americas, globe_asia, globe_europe,
        golf_ball, gopuram, graduation_cap, greater_than, greater_than_equal, grimace, grin, grin_alt, grin_beam,
        grin_beam_sweat, grin_hearts, grin_squint, grin_squint_tears, grin_stars, grin_tears, grin_tongue,
        grin_tongue_squint, grin_tongue_wink, grin_wink, grip_horizontal, grip_lines, grip_lines_vertical,
        grip_vertical, guitar, h_square, hamburger, hammer, hamsa, hand_holding, hand_holding_heart, hand_holding_usd,
        hand_lizard, hand_middle_finger, hand_paper, hand_peace, hand_point_down, hand_point_left, hand_point_right,
        hand_point_up, hand_pointer, hand_rock, hand_scissors, hand_spock, hands, hands_helping, handshake, hanukiah,
        hard_hat, hashtag, hat_wizard, haykal, hdd, heading, headphones, headphones_alt, headset, heart, heart_broken,
        heartbeat, helicopter, highlighter, hiking, hippo, history, hockey_puck, holly_berry, home, horse, horse_head,
        hospital, hospital_alt, hospital_symbol, hot_tub, hotdog, hotel, hourglass, hourglass_end, hourglass_half,
        hourglass_start, house_damage, hryvnia, i_cursor, ice_cream, icicles, id_badge, id_card, id_card_alt, igloo,
        image, images, inbox, indent, industry, infinity, info, info_circle, italic, jedi, joint, journal_whills,
        kaaba, key, keyboard, khanda, kiss, kiss_beam, kiss_wink_heart, kiwi_bird, landmark, language, laptop,
        laptop_code, laptop_medical, laugh, laugh_beam, laugh_squint, laugh_wink, layer_group, leaf, lemon, less_than,
        less_than_equal, level_down_alt, level_up_alt, life_ring, lightbulb, link, lira_sign, list, list_alt, list_ol,
        list_ul, location_arrow, lock, lock_open, long_arrow_alt_down, long_arrow_alt_left, long_arrow_alt_right,
        long_arrow_alt_up, low_vision, luggage_cart, magic, magnet, mail_bulk, male, map, map_marked, map_marked_alt,
        map_marker, map_marker_alt, map_pin, map_signs, marker, mars, mars_double, mars_stroke, mars_stroke_h,
        mars_stroke_v, mask, medal, medkit, meh, meh_blank, meh_rolling_eyes, memory, menorah, mercury, meteor,
        microchip, microphone, microphone_alt, microphone_alt_slash, microphone_slash, microscope, minus, minus_circle,
        minus_square, mitten, mobile, mobile_alt, money_bill, money_bill_alt, money_bill_wave, money_bill_wave_alt,
        money_check, money_check_alt, monument, moon, mortar_pestle, mosque, motorcycle, mountain, mouse_pointer,
        mug_hot, music, network_wired, neuter, newspaper, not_equal, notes_medical, object_group, object_ungroup,
        oil_can, om, otter, outdent, pager, paint_brush, paint_roller, palette, pallet, paper_plane, paperclip,
        parachute_box, paragraph, parking, passport, pastafarianism, paste, pause, pause_circle, paw, peace, pen,
        pen_alt, pen_fancy, pen_nib, pen_square, pencil_alt, pencil_ruler, people_carry, pepper_hot, percent,
        percentage, person_booth, phone, phone_slash, phone_square, phone_volume, piggy_bank, pills, pizza_slice,
        place_of_worship, plane, plane_arrival, plane_departure, play, play_circle, plug, plus, plus_circle,
        plus_square, podcast, poll, poll_h, poo, poo_storm, poop, portrait, pound_sign, power_off, pray, praying_hands,
        prescription, prescription_bottle, prescription_bottle_alt, print, procedures, project_diagram, puzzle_piece,
        qrcode, question, question_circle, quidditch, quote_left, quote_right, quran, radiation, radiation_alt, rainbow,
        random, receipt, recycle, redo, redo_alt, registered, reply, reply_all, republican, restroom, retweet, ribbon,
        ring, road, robot, rocket, route, rss, rss_square, ruble_sign, ruler, ruler_combined, ruler_horizontal,
        ruler_vertical, running, rupee_sign, sad_cry, sad_tear, satellite, satellite_dish, save, school, screwdriver,
        scroll, sd_card, search, search_dollar, search_location, search_minus, search_plus, seedling, server, shapes,
        share, share_alt, share_alt_square, share_square, shekel_sign, shield_alt, ship, shipping_fast, shoe_prints,
        shopping_bag, shopping_basket, shopping_cart, shower, shuttle_van, sign, sign_in_alt, sign_language,
        sign_out_alt, signal, signature, sim_card, sitemap, skating, skiing, skiing_nordic, skull, skull_crossbones,
        slash, sleigh, sliders_h, smile, smile_beam, smile_wink, smog, smoking, smoking_ban, sms, snowboarding,
        snowflake, snowman, snowplow, socks, solar_panel, sort, sort_alpha_down, sort_alpha_up, sort_amount_down,
        sort_amount_up, sort_down, sort_numeric_down, sort_numeric_up, sort_up, spa, space_shuttle, spider, spinner,
        splotch, spray_can, square, square_full, square_root_alt, stamp, star, star_and_crescent, star_half,
        star_half_alt, star_of_david, star_of_life, step_backward, step_forward, stethoscope, sticky_note, stop,
        stop_circle, stopwatch, store, store_alt, stream, street_view, strikethrough, stroopwafel, subscript, subway,
        suitcase, suitcase_rolling, sun, superscript, surprise, swatchbook, swimmer, swimming_pool, synagogue, sync,
        sync_alt, syringe, table, table_tennis, tablet, tablet_alt, tablets, tachometer_alt, tag, tags, tape, tasks,
        taxi, teeth, teeth_open, temperature_high, temperature_low, tenge, terminal, text_height, text_width, th,
        th_large, th_list, theater_masks, thermometer, thermometer_empty, thermometer_full, thermometer_half,
        thermometer_quarter, thermometer_three_quarters, thumbs_down, thumbs_up, thumbtack, ticket_alt, times,
        times_circle, tint, tint_slash, tired, toggle_off, toggle_on, toilet, toilet_paper, toolbox, tools, tooth,
        torah, torii_gate, tractor, trademark, traffic_light, train, tram, transgender, transgender_alt, trash,
        trash_alt, trash_restore, trash_restore_alt, tree, trophy, truck, truck_loading, truck_monster, truck_moving,
        truck_pickup, tshirt, tty, tv, umbrella, umbrella_beach, underline, undo, undo_alt, universal_access,
        university, unlink, unlock, unlock_alt, upload, user, user_alt, user_alt_slash, user_astronaut, user_check,
        user_circle, user_clock, user_cog, user_edit, user_friends, user_graduate, user_injured, user_lock, user_md,
        user_minus, user_ninja, user_nurse, user_plus, user_secret, user_shield, user_slash, user_tag, user_tie,
        user_times, users, users_cog, utensil_spoon, utensils, vector_square, venus, venus_double, venus_mars, vial,
        vials, video, video_slash, vihara, volleyball_ball, volume_down, volume_mute, volume_off, volume_up, vote_yea,
        vr_cardboard, walking, wallet, warehouse, water, weight, weight_hanging, wheelchair, wifi, wind, window_close,
        window_maximize, window_minimize, window_restore, wine_bottle, wine_glass, wine_glass_alt, won_sign, wrench,
        x_ray, yen_sign, yin_yang;

        @Override
        public String getPrefix() {
            return "fas";
        }

        @Override
        public String getIconName() {
            return "fa-" + name();
        }
    }

    /**
     * All icons of style 'regular' available for free in Font Awesome.
     */
    public enum FontAwesome5Regular implements FontAwesome5Graphic {
        address_book, address_card, angry, arrow_alt_circle_down, arrow_alt_circle_left, arrow_alt_circle_right,
        arrow_alt_circle_up, bell, bell_slash, bookmark, building, calendar, calendar_alt, calendar_check,
        calendar_minus, calendar_plus, calendar_times, caret_square_down, caret_square_left, caret_square_right,
        caret_square_up, chart_bar, check_circle, check_square, circle, clipboard, clock, clone, closed_captioning,
        comment, comment_alt, comment_dots, comments, compass, copy, copyright, credit_card, dizzy, dot_circle, edit,
        envelope, envelope_open, eye, eye_slash, file, file_alt, file_archive, file_audio, file_code, file_excel,
        file_image, file_pdf, file_powerpoint, file_video, file_word, flag, flushed, folder, folder_open, frown,
        frown_open, futbol, gem, grimace, grin, grin_alt, grin_beam, grin_beam_sweat, grin_hearts, grin_squint,
        grin_squint_tears, grin_stars, grin_tears, grin_tongue, grin_tongue_squint, grin_tongue_wink, grin_wink,
        hand_lizard, hand_paper, hand_peace, hand_point_down, hand_point_left, hand_point_right, hand_point_up,
        hand_pointer, hand_rock, hand_scissors, hand_spock, handshake, hdd, heart, hospital, hourglass, id_badge,
        id_card, image, images, keyboard, kiss, kiss_beam, kiss_wink_heart, laugh, laugh_beam, laugh_squint, laugh_wink,
        lemon, life_ring, lightbulb, list_alt, map, meh, meh_blank, meh_rolling_eyes, minus_square, money_bill_alt,
        moon, newspaper, object_group, object_ungroup, paper_plane, pause_circle, play_circle, plus_square,
        question_circle, registered, sad_cry, sad_tear, save, share_square, smile, smile_beam, smile_wink, snowflake,
        square, star, star_half, sticky_note, stop_circle, sun, surprise, thumbs_down, thumbs_up, times_circle, tired,
        trash_alt, user, user_circle, window_close, window_maximize, window_minimize, window_restore;

        @Override
        public String getPrefix() {
            return "far";
        }

        @Override
        public String getIconName() {
            return "fa-" + name();
        }
    }

    /**
     * All icons of style 'brand' available for free in Font Awesome.
     */
    public enum FontAwesome5Brand implements FontAwesome5Graphic {
        _500px, accessible_icon, accusoft, acquisitions_incorporated, adn, adobe, adversal, affiliatetheme, algolia,
        alipay, amazon, amazon_pay, amilia, android, angellist, angrycreative, angular, app_store, app_store_ios, apper,
        apple, apple_pay, artstation, asymmetrik, atlassian, audible, autoprefixer, avianex, aviato, aws, bandcamp,
        behance, behance_square, bimobject, bitbucket, bitcoin, bity, black_tie, blackberry, blogger, blogger_b,
        bluetooth, bluetooth_b, btc, buromobelexperte, buysellads, canadian_maple_leaf, cc_amazon_pay, cc_amex,
        cc_apple_pay, cc_diners_club, cc_discover, cc_jcb, cc_mastercard, cc_paypal, cc_stripe, cc_visa, centercode,
        centos, chrome, cloudscale, cloudsmith, cloudversify, codepen, codiepie, confluence, connectdevelop, contao,
        cpanel, creative_commons, creative_commons_by, creative_commons_nc, creative_commons_nc_eu,
        creative_commons_nc_jp, creative_commons_nd, creative_commons_pd, creative_commons_pd_alt,
        creative_commons_remix, creative_commons_sa, creative_commons_sampling, creative_commons_sampling_plus,
        creative_commons_share, creative_commons_zero, critical_role, css3, css3_alt, cuttlefish, d_and_d,
        d_and_d_beyond, dashcube, delicious, deploydog, deskpro, dev, deviantart, dhl, diaspora, digg, digital_ocean,
        discord, discourse, dochub, docker, draft2digital, dribbble, dribbble_square, dropbox, drupal, dyalog,
        earlybirds, ebay, edge, elementor, ello, ember, empire, envira, erlang, ethereum, etsy, expeditedssl, facebook,
        facebook_f, facebook_messenger, facebook_square, fantasy_flight_games, fedex, fedora, figma, firefox,
        first_order, first_order_alt, firstdraft, flickr, flipboard, fly, font_awesome, font_awesome_alt,
        font_awesome_flag, fonticons, fonticons_fi, fort_awesome, fort_awesome_alt, forumbee;

        @Override
        public String getPrefix() {
            return "fab";
        }

        @Override
        public String getIconName() {
            if (this == _500px)
                return "fa-500px";
            else
                return "fa-" + name();
        }
    }

    /**
     * Rotation that can be done on an icon.
     */
    public enum Rotation {
        flip_horizontal, flip_vertical, normal, rotate_180, rotate_270, rotate_90
    }

    /**
     * Sizes on an icon.
     */
    public enum Size {
        two("2x"), three("3x"), four("4x"), five("5x"), large("lg");

        private String style;

        Size(final String factor) {
            this.style = "fa-" + factor;
        }}

    /**
     * @param fontAwesomeGraphic icon to use in the builder
     * @return a builder for this icon
     */
    public static FontAwesome5IconTypeBuilder on(final FontAwesome5Graphic fontAwesomeGraphic) {
        return new FontAwesome5IconTypeBuilder(fontAwesomeGraphic);
    }

    /**
     * Icon used in the builder.
     */
    private final FontAwesome5Graphic fontAwesomeGraphic;
    /**
     * rotation to apply to the icon (default none).
     */
    private Rotation rotation;
    /**
     * size to apply to the icon (by default *1).
     */
    private Size size;
    /**
     * Do we have to make the icon spin?
     */
    private boolean spin;

    private boolean fixedWidth;

    /**
     * @param fontAwesomeGraphic icon to use in the builder
     */
    private FontAwesome5IconTypeBuilder(final FontAwesome5Graphic fontAwesomeGraphic) {
        this.fontAwesomeGraphic = fontAwesomeGraphic;
    }

    /**
     * @return build the icon
     */
    public FontAwesome5IconType build() {
        final List<String> styles = new ArrayList<>();

        styles.add(fontAwesomeGraphic.getPrefix());

        // replace all underscore to dashes
        styles.add(underscoresToDashes(fontAwesomeGraphic.getIconName()));

        // add spin class?
        if (spin) {
            styles.add("fa-spin");
        }

        if (fixedWidth) {
            styles.add("fa-fw");
        }

        // add rotation class?
        if (rotation != null) {
            styles.add("fa-" + underscoresToDashes(rotation.name()));
        }

        // add size class
        if (size != null) {
            styles.add(size.style);
        }

        return new FontAwesome5IconType(styles.toArray(new String[0]));
    }

    /**
     * @param rotation rotation to apply to the icon
     * @return the builder
     */
    public FontAwesome5IconTypeBuilder rotate(final Rotation rotation) {
        this.rotation = rotation;
        return this;
    }

    /**
     * @param size size to apply to the icon
     * @return the builder
     */
    public FontAwesome5IconTypeBuilder size(final Size size) {
        this.size = size;
        return this;
    }

    /**
     * make the icon spin
     *
     * @return the builder
     */
    public FontAwesome5IconTypeBuilder spin() {
        this.spin = true;
        return this;
    }

    /**
     * make the icon fixed width
     *
     * @return the builder
     */
    public FontAwesome5IconTypeBuilder fixedWidth() {
        this.fixedWidth = true;
        return this;
    }

    /**
     * An alias for {@link #fixedWidth()}
     *
     * @return the builder
     */
    public FontAwesome5IconTypeBuilder fw() {
        return fixedWidth();
    }

    /**
     * @param string string to work on!
     * @return the string with the underscores replace with dashes
     */
    private String underscoresToDashes(final String string) {
        return string.replace('_', '-');
    }
}
